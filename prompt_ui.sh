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

echo "## handler.clj" >> output.md
echo $'```clojure' >> output.md
cat "$PWD/src/hs_clojure/handler.clj" >> output.md
echo $'```' >> output.md
echo "" >> output.md

echo "## routes.clj" >> output.md
echo $'```clojure' >> output.md
cat "$PWD/src/hs_clojure/routes.clj" >> output.md
echo $'```' >> output.md
echo "" >> output.md

echo "## patients.clj" >> output.md
echo $'```clojure' >> output.md
cat "$PWD/src/hs_clojure/patients.clj" >> output.md
echo $'```' >> output.md
echo "" >> output.md

echo "## Index page" >> output.md
echo $'```html' >> output.md
cat "$PWD/resources/public/index.html" >> output.md
echo $'```' >> output.md
echo "" >> output.md
