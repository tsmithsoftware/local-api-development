import 'package:flutter/material.dart';

class PageLoadingWidget extends StatelessWidget {
  const PageLoadingWidget({super.key});

  @override
  Widget build(BuildContext context) {
    return SizedBox(
      height: MediaQuery.of(context).size.height / 3,
      child: const Center(
        child: CircularProgressIndicator(),
      ),
    );
  }
}