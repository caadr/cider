# Use an official Clojure image as parent
FROM clojure:temurin-17-tools-deps-bullseye

# PATH "hardening"
#
# Upstream does not install the jdk via apt, but (1) copies it to /opt and
# (2) adds it to PATH in an ENV directive. These are available when using docker
# commands like run or exec, but will not propagate through ssh.
#
# Moreover, when executing a shell-command via tramp, it won't lookup the user-specific
# PATH by default. Instead, it searches for the executable in conventional directories
# like /usr/bin and the remotes initial `getconf PATH`.
#
# Lastly, in the case of running a login shell, PATH will be overwritten by /etc/profile
# with a hardcoded value.
# 
# To circumvent these and other potential issues, we ensure that java
# is symlinked to the expected places.
RUN ln -sv $JAVA_HOME/bin/* /usr/bin/

# Persisting upstreams other envars to /etc/environment ensures that they are available
# from *everywhere* unless overwritten.
RUN echo JAVA_HOME=$JAVA_HOME  >> /etc/environment && \
    echo CLOJURE_VERSION=$CLOJURE_VERSION >> /etc/environment

# SSH server setup
RUN apt-get update && apt-get install -y \
    openssh-server && \
    # Start as a service once, to init necessary runtime state
    service ssh start && \
    # !   Allow remote root access   !
    # !       password: cider        !
    sed -i 's/^#* *PermitRootLogin .*/PermitRootLogin yes/' /etc/ssh/sshd_config && \
    echo 'root:cider' | chpasswd
EXPOSE 22

# Project folder initialization
COPY . /usr/src/app
WORKDIR /usr/src/app
RUN clojure -P

# Run the ssh server. [-D]on't be a daemon. Log to std[-e]rr.
ENTRYPOINT ["/usr/sbin/sshd","-De"]
