build: remove-from-known-hosts
	docker build -t cider-tramp-dev-lein .

run: build
	docker run -p 8022:22 cider-tramp-dev-lein

remove-from-known-hosts:
	ssh-keygen -R '[localhost]:8022'

ssh-login:
	echo "Password is: cider"
	ssh root@localhost -p 8022

stop: 
	docker stop `(docker ps --quiet --filter ancestor=cider-tramp-dev-lein)` || exit 0

cleanup: stop
	docker remove `(docker ps --all --quiet --filter ancestor=cider-tramp-dev-lein)`
