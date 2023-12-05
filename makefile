# Define variables
SRC_DIR = src
BACKEND_DIR = src/backend
FRONTEND_DIR = src/fronted
BIN_DIR = bin
JAVAC = javac
JAVA = java
JFLAGS = -d $(BIN_DIR) --enable-preview --source 21
CP = -cp $(BIN_DIR):lib/mysql-connector-java-8.0.22.jar

# Define Java source files
SRCS = $(wildcard $(SRC_DIR)/**/*.java)
SRCS2 = $(wildcard $(SRC_DIR)/backend/controllers/*.java)

# Define class files
CLASSES = $(patsubst $(SRC_DIR)/%.java,$(BIN_DIR)/%.class,$(SRCS))
CONTROLLERS = $(patsubst $(SRC_DIR)/backend/controllers/%.java,$(BIN_DIR)/backend/controllers/%.class,$(SRCS2))
VARIABLE_SRC = $(SRC_DIR)/backend/VariablesSingleton.java
VARIABLE_CLASS = $(BIN_DIR)/VariablesSingleton.class

# Default target
all: $(VARIABLE_CLASS) $(CLASSES) $(CONTROLLERS) $(ABSTRACTS)

# Compile .java files to .class files
$(VARIABLE_CLASS): $(VARIABLE_SRC)
	@mkdir -p $(@D)
	$(JAVAC) $(JFLAGS) $(CP) $<

$(BIN_DIR)/backend/controllers/%.class: $(CONTROLLERS)/%.java
	@mkdir -p $(@D)
	$(JAVAC) $(JFLAGS) $(CP) $<

$(BIN_DIR)/%.class: $(SRC_DIR)/%.java
	@mkdir -p $(@D)
	$(JAVAC) $(JFLAGS) $(CP) $<

# Run the main class
run: all
	$(JAVA) $(CP) --enable-preview --source 21 src/App.java

# Clean compiled files
clean:
	rm -rf $(BIN_DIR)

# Phony targets
.PHONY: all run clean
