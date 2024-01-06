JC = javac
JFLAGS = -sourcepath ./src -d bin --enable-preview --source 21 -g --module-path lib/javafx --add-modules javafx.controls,javafx.fxml,javafx.swing,javafx.web,javafx.base,javafx.graphics,javafx.media

.SUFFIXES: .java .class

SRC_DIR = ./src
BUILD_DIR = ./bin

SRCS = $(shell find $(SRC_DIR) -name "*.java")
CLASSES = $(SRCS:$(SRC_DIR)/%.java=$(BUILD_DIR)/%.class)
FXML_FILES := $(wildcard $(SRC_DIR)/frontend/views/*.fxml)

default: move_fxml_files

move_fxml_files: classes
	@mkdir -p $(BUILD_DIR)/frontend/views
	@cp $(FXML_FILES) $(BUILD_DIR)/frontend/views

$(BUILD_DIR)/%.class: $(SRC_DIR)/%.java
	@mkdir -p $(@D)
	$(JC) $(JFLAGS) $<

classes: $(CLASSES)

run: default
	java --enable-preview -cp bin:lib/mysql-connector-java-8.0.22.jar:lib/javafx/javafx-swt.jar:lib/javafx/javafx.base.jar:lib/javafx/javafx.controls.jar:lib/javafx/javafx.fxml.jar:lib/javafx/javafx.graphics.jar:lib/javafx/javafx.media.jar:lib/javafx/javafx.swing.jar:lib/javafx/javafx.web.jar App

clean: 
	rm -rf bin