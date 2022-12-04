import 'package:flutter/material.dart';
import 'package:get/get.dart';

class ErrorDisplayWidget extends StatelessWidget {
  final String _message;
  const ErrorDisplayWidget(this._message, {super.key});

  @override
  Widget build(BuildContext context) {
    return Center(
      child: SizedBox(
        height: 200,
        width: 500,
        child: Dialog(
          child: Card(
            child: Column(
              children: [
                Row(
                  children: [
                    const Icon(Icons.error),
                    Text(_message),
                  ],
                ),
                MaterialButton(onPressed: (){
                  Get.back();
                }, child: const Text("Go Back"))
              ],
            ),
          ),
        ),
      ),
    );
  }
}