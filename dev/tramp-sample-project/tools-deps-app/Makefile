build: remove-from-known-hosts
	docker build -t cider-tramp-dev-tools-deps .

run: build
	docker run -p 9022:22 cider-tramp-dev-tools-deps

remove-from-known-hosts:
	ssh-keygen -R '[localhost]:9022'

ssh-login:
	echo "Password is: cider"
	ssh root@localhost -p 9022

stop:
	docker stop `(docker ps --quiet --filter ancestor=cider-tramp-dev-tools-deps)` || exit 0

cleanup: stop
	docker remove `(docker ps --all --quiet --filter ancestor=cider-tramp-dev-tools-deps)`
