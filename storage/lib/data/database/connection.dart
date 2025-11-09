import 'package:flutter/material.dart';

class InMemoryCounter extends StatefulWidget {
  const InMemoryCounter({super.key});

  @override
  State<InMemoryCounter> createState() => _InMemoryCounterState();
}

class _InMemoryCounterState extends State<InMemoryCounter> {
  int _counter = 0;

  void _increment() {
    setState(() => _counter++);
  }

  void _reset() {
    setState(() => _counter = 0);
  }

  @override
  Widget build(BuildContext context) {
    return Center(
      child: Column(
        mainAxisAlignment: MainAxisAlignment.center,
        children: [
          Text('Counter (in-memory): $_counter', style: const TextStyle(fontSize: 24)),
          const SizedBox(height: 20),
          ElevatedButton(onPressed: _increment, child: const Text('Increment')),
          const SizedBox(height: 10),
          ElevatedButton(onPressed: _reset, child: const Text('Reset')),
        ],
      ),
    );
  }
}
