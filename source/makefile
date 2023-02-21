#------------------------------------------------------------------------------#
# Project name & files                                                         #
#------------------------------------------------------------------------------#
PROJECT_NAME        := zodiac
JAR_FILE            := $(PROJECT_NAME).jar
MANIFEST_FILE       := manifest.mf

JAVA_FILES          := $(shell find -type f -name '*.java')
JAVA_SRC_FILES      := $(shell find -type f -name '[A-Z]*.java')
CLASS_FILES         := $(JAVA_SRC_FILES:%.java=%.class)

COMPILER_FLAGS      := -Xlint

RUN_ARGS            :=

#------------------------------------------------------------------------------#
# Commands                                                                     #
#------------------------------------------------------------------------------#
.PHONY : all, run, clean
all :
	javac $(COMPILER_FLAGS) $(JAVA_SRC_FILES)
	jar cfmv $(JAR_FILE) $(MANIFEST_FILE) $(CLASS_FILES)
run : all
	mkdir -p /tmp/$(PROJECT_NAME)
	java -jar $(JAR_FILE) $(RUN_ARGS)
clean :
	rm -v $(JAR_FILE) $(CLASS_FILES)
doc :
	javadoc -private -d ../documentation $(JAVA_FILES)
