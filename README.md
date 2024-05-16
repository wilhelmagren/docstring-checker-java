# docstring-checker-java
Check that all docstrings in a project have been updated to match latest changes, written in Java.

# Usage

You need java and maven installed on your system. Run something like `apt install maven` if you dont have it installed, if you have apt on your system, otherwise do something else, find out how to do it :)

if you wanna compile and run from the root of the project run the `compile_and_run.sh` shell script. You should get an output something like this:
```
[INFO] ------------------------------------------------------------------------

 🎉 There were no outdated docstrings. Great job!

```

If you have no outdated docstrings. If you have outdated docstrings, the program will output something like this:
```
[INFO] ------------------------------------------------------------------------

 🧸 Invalid docstring in file: ./src/main/java/docstringchecker/FileChecker.java
    - Date in file:  2024-05-01
    - Last modified: 2024-05-16

 🧸 Invalid docstring in file: ./src/main/java/docstringchecker/FileCollection.java
    - Date in file:  2012-12-12
    - Last modified: 2024-05-16

 💥 There were 2 outdated docstring(s) in total. Resolve this please!

```

That's about it. Bad program, don't use :)
