JC = javac
JFLAGS = -sourcepath ./src -d bin --enable-preview --source 21 -g

.SUFFIXES: .java .class

SRC_DIR = ./src
BUILD_DIR = ./bin

SRCS = $(shell find $(SRC_DIR) -name "*.java")
CLASSES = $(SRCS:$(SRC_DIR)/%.java=$(BUILD_DIR)/%.class)

default: classes

$(BUILD_DIR)/%.class: $(SRC_DIR)/%.java
	@mkdir -p $(@D)
	$(JC) $(JFLAGS) $<

classes: $(CLASSES)

run: default
	java --enable-preview -cp bin:lib/mysql-connector-java-8.0.22.jar App

clean: 
	rm -rf bin