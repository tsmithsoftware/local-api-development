import 'package:flutter/material.dart';

class ProjectLogo extends StatelessWidget {
  /// null.
  const ProjectLogo({
    super.key,
    this.size,
    this.textColor = const Color(0xFF757575),
    this.style = FlutterLogoStyle.markOnly,
    this.duration = const Duration(milliseconds: 750),
    this.curve = Curves.fastOutSlowIn,
  });

  /// The size of the logo in logical pixels.
  ///
  /// The logo will be fit into a square this size.
  ///
  /// Defaults to the current [IconTheme] size, if any. If there is no
  /// [IconTheme], or it does not specify an explicit size, then it defaults to
  /// 24.0.
  final double? size;

  /// The color used to paint the "Flutter" text on the logo, if [style] is
  /// [FlutterLogoStyle.horizontal] or [FlutterLogoStyle.stacked].
  ///
  /// If possible, the default (a medium grey) should be used against a white
  /// background.
  final Color textColor;

  /// Whether and where to draw the "Flutter" text. By default, only the logo
  /// itself is drawn.
  final FlutterLogoStyle style;

  /// The length of time for the animation if the [style] or [textColor]
  /// properties are changed.
  final Duration duration;

  /// The curve for the logo animation if the [style] or [textColor] change.
  final Curve curve;

  @override
  Widget build(BuildContext context) {
    final IconThemeData iconTheme = IconTheme.of(context);
    final double? iconSize = size ?? iconTheme.size;
    return AnimatedContainer(
      width: iconSize,
      height: iconSize,
      duration: duration,
      curve: curve,
      child: Image.asset('assets/logo.png')
    );
  }
}