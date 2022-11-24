import 'dart:ui';

import 'package:flutter/material.dart';
import 'package:get/get.dart';
import 'package:user_management/utils/widgets/animated_fab.dart';

import 'features/list_users/presentation/list_users_page.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return GetMaterialApp(
      title: 'Flutter Demo',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: const MyHomePage(title: 'Flutter Demo Home Page'),
      getPages: [
        GetPage(name: ListUsersPage.id, page: () => const ListUsersPage())
      ],
    );
  }
}

class MyHomePage extends StatefulWidget {
  const MyHomePage({super.key, required this.title});
  final String title;

  @override
  State<MyHomePage> createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> with SingleTickerProviderStateMixin {
  late Animation<double> animation;
  late AnimationController controller;

  @override
  void initState() {
    super.initState();
    controller = AnimationController(duration: const Duration(seconds: 5), vsync: this);
    var screenWidth = (window.physicalSize.shortestSide / window.devicePixelRatio);
    Tween<double> animationOne = Tween<double>(begin: 0, end: screenWidth);
    animation = animationOne.animate(controller);
    controller.forward();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(widget.title),
      ),
      body: RefreshIndicator(
        onRefresh: () async {
          if(controller.isCompleted) {
            controller.reverse();
          } else {
            controller.forward();
          }
        },
        child: Center(
          child: AnimatedContainer(
              duration: const Duration(seconds: 5),
          width: animation.value,
          height: animation.value,
          child: Image.asset('assets/logo.png')),
        ),
      ),
      floatingActionButton: FloatingActionButton(
        onPressed: () => Get.toNamed(ListUsersPage.id),
        tooltip: 'Increment',
        child: const AnimatedFab(),
      ),
    );
  }

  @override
  void dispose() {
    controller.dispose();
    super.dispose();
  }
}
