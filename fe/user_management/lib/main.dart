
import 'package:flutter/material.dart';
import 'package:get/get.dart';
import 'package:user_management/utils/widgets/animated_fab.dart';
import 'package:flutter_localizations/flutter_localizations.dart';
import 'features/list_users/presentation/list_users_page.dart';
import 'package:flutter_gen/gen_l10n/app_localizations.dart';

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
      localizationsDelegates: const [
        AppLocalizations.delegate,
        GlobalMaterialLocalizations.delegate,
        GlobalWidgetsLocalizations.delegate,
        GlobalCupertinoLocalizations.delegate,
      ],
      supportedLocales: const [
        Locale('en', ''),
        Locale('es', ''),
      ],
      home: const MyHomePage(),
      getPages: [
        GetPage(name: ListUsersPage.id, page: () => const ListUsersPage())
      ],
    );
  }
}

class MyHomePage extends StatefulWidget {
  const MyHomePage({super.key});

  @override
  State<MyHomePage> createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage>
    with SingleTickerProviderStateMixin {

  double currentSize = 0.0;
  final double animateToSize = 600.0;
  final double animateFromSize = 0.0;

  @override
  void initState() {
    Future.delayed(const Duration(seconds: 2)).then((value) => setState(() {
      currentSize = animateToSize;
    }));
    super.initState();

  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(AppLocalizations.of(context)!.helloWorld),
      ),
      body: RefreshIndicator(
        onRefresh: () async {
          setState(() {
            if(currentSize == animateFromSize) {
              currentSize = animateToSize;
            } else {
              currentSize = animateFromSize;
            }
          });
        },
        child: SingleChildScrollView(
          physics: const AlwaysScrollableScrollPhysics(),
            child: SizedBox(
              height: MediaQuery.of(context).size.height,
              width: MediaQuery.of(context).size.width,
              child: Center(
                child: AnimatedContainer(
                    duration: const Duration(seconds: 5),
                    curve: Curves.easeInOut,
                    width: currentSize,
                    height: currentSize,
                    child: Image.asset('assets/logo.png')),
              ),
            ),
          ),
        ),
     // ),
      floatingActionButton: FloatingActionButton(
        onPressed: () => Get.toNamed(ListUsersPage.id),
        tooltip: 'Increment',
        child: const AnimatedFab(),
      ),
    );
  }
}
