compile:
	javac -cp .:libs/postgresql-42.7.4.jar *.java

run:
	java -cp .:libs/postgresql-42.7.4.jar MainApp
