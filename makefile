# Define variables
SRC_DIR = src
BIN_DIR = bin
JAVAC = javac
JAVA = java
JFLAGS = -d $(BIN_DIR)
CP = -cp $(BIN_DIR):lib/mysql-connector-java-8.0.22.jar

# Define Java source files
SRCS = $(wildcard $(SRC_DIR)/**/*.java)

# Define class files
CLASSES = $(patsubst $(SRC_DIR)/%.java,$(BIN_DIR)/%.class,$(SRCS))
VARIABLE_SRC = $(SRC_DIR)/backend/Variables.java
VARIABLE_CLASS = $(BIN_DIR)/Variables.class

# Default target
all: $(VARIABLE_CLASS) $(CLASSES)

# Compile .java files to .class files
$(VARIABLE_CLASS): $(VARIABLE_SRC)
	@mkdir -p $(@D)
	$(JAVAC) $(JFLAGS) $(CP) $<

$(BIN_DIR)/%.class: $(SRC_DIR)/%.java
	@mkdir -p $(@D)
	$(JAVAC) $(JFLAGS) $(CP) $<

# Run the main class
run: all
	$(JAVA) $(CP) --enable-preview fitness.App

# Clean compiled files
clean:
	rm -rf $(BIN_DIR)

# Phony targets
.PHONY: all run clean
