package com.colinodell.advent2023

class Day20(private val input: List<String>) {
    private enum class Pulse { LOW, HIGH }

    private sealed class Module(val name: String) {
        open fun onReceive(p: Pulse, from: String) {}
        open fun sendPulse(lastPulse: Pulse): Pulse? = null

        override fun toString() = "[${this::class.simpleName}] $name"

        class Button : Module("button") {
            override fun sendPulse(lastPulse: Pulse) = Pulse.LOW
        }

        class Broadcaster : Module("broadcaster") {
            override fun sendPulse(lastPulse: Pulse) = lastPulse
        }

        class FlipFlop(name: String) : Module(name) {
            private var on = false

            override fun onReceive(p: Pulse, from: String) {
                if (p == Pulse.LOW) {
                    on = !on
                }
            }

            override fun sendPulse(lastPulse: Pulse) = when {
                lastPulse == Pulse.HIGH -> null
                on -> Pulse.HIGH
                else -> Pulse.LOW
            }
        }

        class Conjunction(name: String) : Module(name) {
            private val lastPulses = mutableMapOf<String, Pulse>()

            fun expectInput(input: String) {
                lastPulses[input] = Pulse.LOW
            }

            fun getInputs() = lastPulses.keys

            override fun onReceive(p: Pulse, from: String) = lastPulses.set(from, p)

            override fun sendPulse(lastPulse: Pulse) = if (lastPulses.values.all { it == Pulse.HIGH }) Pulse.LOW else Pulse.HIGH
        }

        class Untyped(name: String) : Module(name)

        companion object {
            fun create(name: String) = when {
                name == "button" -> Button()
                name == "broadcaster" -> Broadcaster()
                name.first() == '%' -> FlipFlop(name.drop(1))
                name.first() == '&' -> Conjunction(name.drop(1))
                else -> Untyped(name)
            }
        }
    }

    private val receivers = mapOf("button" to listOf("broadcaster")) + input
        .map { it.split(" -> ") }
        .associate { (source, targets) -> source.trimStart('&', '%') to targets.split(", ") }

    private val modules = buildMap {
        // Button always exists
        this["button"] = Module.Button()

        // Add modules based on the input names
        input
            .map { it.split(" -> ") }
            .map { (source, _) -> Module.create(source) }
            .forEach { this[it.name] = it }

        // Add any output-only modules
        receivers.values.flatten().filter { it !in this }.forEach { this[it] = Module.Untyped(it) }
    }

    init {
        // Tell conjunctions about their expected inputs
        receivers
            .flatMap { (input, receivers) ->
                receivers
                    .filter { modules[it] is Module.Conjunction }
                    .map { input to (modules[it] as Module.Conjunction) }
            }
            .forEach { (input, conjunction) ->
                conjunction.expectInput(input)
            }
    }

    private fun pressButton(
        onPulseReceived: (Module, Pulse) -> Unit = { _, _ -> },
        onPulseSent: (Module, Pulse) -> Unit = { _, _ -> }
    ) {
        val queue = mutableListOf(modules["button"]!! to Pulse.LOW)
        while (queue.isNotEmpty()) {
            val (module, receivedPulse) = queue.removeAt(0)

            // Send the pulse
            val output = module.sendPulse(receivedPulse) ?: continue
            onPulseSent(module, output)

            // Receive the pulse
            for (destination in receivers[module.name]!!.map { modules[it]!! }) {
                destination.onReceive(output, module.name)

                onPulseReceived(destination, output)

                queue.add(destination to output)
            }
        }
    }

    fun solvePart1(): Int {
        var lowPulses = 0
        var highPulses = 0

        repeat(1000) {
            pressButton(onPulseReceived = { _, pulse ->
                when (pulse) {
                    Pulse.LOW -> lowPulses++
                    Pulse.HIGH -> highPulses++
                }
            })
        }

        return lowPulses * highPulses
    }

    fun solvePart2(): Long {
        val target = modules["rx"]!!

        // Find the conjunction that feeds into the target
        val conjunction = receivers
            .filter { (_, receivers) -> target.name in receivers }
            .map { (source, _) -> modules[source]!! }
            .filterIsInstance<Module.Conjunction>()
            .first()

        // What are the inputs to the conjunction?
        val inputs = conjunction.getInputs().map { modules[it]!! }

        // Assume these inputs receive pulses on a cycle; find the cycle length
        val cycleLength = inputs.associate { it.name to 0L }.toMutableMap()

        var currentCycle = 0L
        while (cycleLength.any { it.value == 0L }) {
            currentCycle++

            pressButton(onPulseSent = { module, pulse ->
                if (module in inputs && pulse == Pulse.HIGH) {
                    cycleLength[module.name] = currentCycle
                }
            })
        }

        return cycleLength.values.lcm()
    }
}
