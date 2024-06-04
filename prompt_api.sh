#!/bin/sh

# Get the current working directory
PWD=$(pwd)

# Create a new Markdown file
touch output.md

# Write the code blocks
echo "## project.clj" >> output.md
echo $'```clojure' >> output.md
cat "$PWD/project.clj" >> output.md
echo $'```' >> output.md
echo "" >> output.md

echo "## core.clj" >> output.md
echo $'```clojure' >> output.md
cat "$PWD/src/hs_clojure/core.clj" >> output.md
echo $'```' >> output.md
echo "" >> output.md

echo "## patients.clj" >> output.md
echo $'```clojure' >> output.md
cat "$PWD/src/hs_clojure/patients.clj" >> output.md
echo $'```' >> output.md
echo "" >> output.md

echo "## db.patients" >> output.md
echo $'```clojure' >> output.md
cat "$PWD/src/hs_clojure/db/patients.clj" >> output.md
echo $'```' >> output.md
echo "" >> output.md

echo "## db connection" >> output.md
echo $'```clojure' >> output.md
cat "$PWD/src/hs_clojure/db.clj" >> output.md
echo $'```' >> output.md
echo "" >> output.md

echo "## db schema" >> output.md
echo $'```clojure' >> output.md
cat "$PWD/src/hs_clojure/db/sql/patients.sql" >> output.md
echo $'```' >> output.md
echo "" >> output.md
