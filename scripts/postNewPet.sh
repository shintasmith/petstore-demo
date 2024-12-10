#!/bin/sh

usage() {
    echo "Usage: `basename $0` <path_to_input_file_json>"
    exit 1
}

ENDPOINT_URL="http://localhost:8080"

if [ $# -lt 1 ]; then
    usage
fi

INPUT_FILE=$1

curl -X POST -H "Content-Type: application/json" $ENDPOINT_URL/pet -d@${INPUT_FILE}
