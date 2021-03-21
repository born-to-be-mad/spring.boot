#!/usr/bin/env bash
set -euo pipefail
which docker > /dev/null || (echoerr "Please ensure that docker is in your PATH" && exit 1)

docker run --rm -d --name rabbit-docker -p 5671:5671 -p 5672:5672 -p 15672:15672 rabbitmq:3.8.9-management
