#!/usr/bin/env bash
set -euo pipefail
which docker > /dev/null || (echoerr "Please ensure that docker is in your PATH" && exit 1)
docker stop rabbit-docker