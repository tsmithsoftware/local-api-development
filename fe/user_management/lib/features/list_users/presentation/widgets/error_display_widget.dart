import 'package:flutter/material.dart';

class ErrorDisplayWidget extends StatelessWidget {
  final String _message;
  final Function onWidgetRetry;
  final Function onWidgetDismissed;
  const ErrorDisplayWidget(this._message,
      {super.key,
      required this.onWidgetRetry,
      required this.onWidgetDismissed});

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
                MaterialButton(
                    onPressed: () => onWidgetRetry(),
                    child: const Text("Retry")),
                MaterialButton(
                    onPressed: () => onWidgetDismissed(),
                    child: const Text("Cancel"))
              ],
            ),
          ),
        ),
      ),
    );
  }
}
