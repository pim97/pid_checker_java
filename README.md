# pid_checker_java
will check for specific name in the process and adds them to a pid list, if existing too long, will kill it. including commands

# How to use

# PidManager
Arraylist listenToNames will be the names it listens to to track the pids

# CommandsManager
Currently 2 added commands, you can type these in when running: -l (will display a list of all pids) and -ka(will kill all pids in program)

# Process
Object containing info about process

# Threadmanager
The manager for the 2 different threads: checking pids and checking for commands (input) in console
