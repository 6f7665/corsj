
# compiler
JC = javac

# flags
JFLAGS = -g

CLASSES = HttpServer.class HttpRequestHandler.class

all: app

app: $(CLASSES)

clean: rm -f *.class

%.class: %.java
	$(JC) $<

jar: $(CLASSES)
	jar cmvf MANIFEST.txt app.jar $(CLASSES)

.PHONY: all app clean jar
