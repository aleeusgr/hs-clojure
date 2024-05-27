#!/bin/sh

# Set the API URL and patient data
API_URL="http://localhost:3000/patients"
NAME="John Doe"
SEX="M"
DATE_OF_BIRTH="1990-01-01"
ADDRESS="123 Main St"
SOCIAL_SECURITY_NUMBER="123-45-6789"

# Set the curl command
curl_cmd="curl -X POST \
  $API_URL \
  -H 'Content-Type: application/x-www-form-urlencoded' \
  -d 'name=$NAME&sex=$SEX&date_of_birth=$DATE_OF_BIRTH&address=$ADDRESS&social_security_number=$SOCIAL_SECURITY_NUMBER'"

# Run the curl command
echo "Adding patient..."
eval $curl_cmd
echo "Patient added!"
