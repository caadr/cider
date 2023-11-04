This project spins up a Clojure project within a Docker image.

The Docker image exposes a SSH server.

This way, for development purposes, we can SSH into it with TRAMP and exercise CIDER's TRAMP-related capabilities.

## Some ways to get started:

### Using babashka

* Use `bb run lein` or `bb run tools-deps` to start a container
* Follow the on screen instructions
* Stop the container[s] with `bb stop lein`, `bb stop tools-deps`
* Remove them with `bb clean lein`, `bb clean tools-deps`
* See `bb.edn` for all commands and how this is wired up


###  Manually create a remote repl and connect to it
* cd to `./lein`
* In one terminal tab, run `make run` to run the Docker image
* Once it's ready, from another tab, run `make ssh` and start a repl manually from there
  * The password is `cider`
  * `cd /usr/src/app; lein repl :headless :host 0.0.0.0 :port 7888`

Now, from emacs you can `cider-connect` to localhost.

* `M-:`, `(dired "/sshx:root@localhost#8022:/usr/src/app")`
* `M-x cider-connect` (choose `localhost`, `7888`)

NOTE: Do not visit `foo.clj` directly - do it from dired instead.

