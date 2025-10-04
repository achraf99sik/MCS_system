javac -d out $(find src -name "*.java")

jar -cvfm App.jar manifest.mf -C out .

java -jar App.jar