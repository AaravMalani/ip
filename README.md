# Arthur Dent: A Bewildered Chat Bot 

This is a greenfield Java project. It's based on [Arthur Dent](https://en.wikipedia.org/wiki/Arthur_Dent), the protagonist of the novel series [The Hitchhiker's Guide to the Galaxy](https://en.wikipedia.org/wiki/The_Hitchhiker%27s_Guide_to_the_Galaxy). Given below are instructions on how to use it.

## Setting up in Intellij

Prerequisites: JDK 25, update Intellij to the most recent version.

1. Open Intellij (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project first)
1. Open the project into Intellij as follows:
   1. Click `Open`.
   1. Select the project directory, and click `OK`.
   1. If there are any further prompts, accept the defaults.
1. Configure the project to use **JDK 25** (not other versions) as explained in [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).<br>
   In the same dialog, set the **Project language level** field to the `SDK default` option.
1. After that, locate the `src/main/java/ArthurDent.java` file, right-click it, and choose `Run ArthurDent.main()` (if the code editor is showing compile errors, try restarting the IDE). If the setup is correct, you should see something like the below as the output:
   ```
       _    ____ _____ _   _ _   _ ____
      / \  |  _ \_   _| | | | | | |  _ \
     / _ \ | |_) || | | |_| | | | | |_) |
    / ___ \|  _ < | | |  _  | |_| |  _ <
   /_/   \_\_| \_\|_| |_| |_|\___/|_| \_\
   ```

**Warning:** Keep the `src\main\java` folder as the root folder for Java files (i.e., don't rename those folders or move Java files to another folder outside of this folder path), as this is the default location some tools (e.g., Gradle) expect to find Java files.

## AI Acknowledgements
- Localised changes are attributed in the class code itself with the `// AI-assisted` prefix.
- Inline code completion was also used to assist with development.