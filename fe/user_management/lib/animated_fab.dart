import 'package:flutter/material.dart';

class AnimatedFab extends StatefulWidget
 {
  const AnimatedFab({super.key});

  @override
  State<StatefulWidget> createState() => AnimatedFabState();
}

class AnimatedFabState extends State<AnimatedFab> with SingleTickerProviderStateMixin {
  late AnimationController controller;

  @override
  void initState() {
    super.initState();
    controller = AnimationController(vsync: this, duration: const Duration(seconds: 5))
    ..forward()
    ..addListener(() {
      if(controller.isCompleted) {
        controller.repeat();
      }
    });
  }

  @override
  Widget build(BuildContext context) {
    return RotationTransition(
      turns:  CurvedAnimation(parent: controller, curve: Curves.easeOut),
      child: const CircleAvatar(
        child: Icon(
          Icons.audiotrack,
          color: Colors.green,
          size: 30.0,
        ),
      ),
    );
  }
}