FROM tmdb_movies01.it_soft.global:5000/runner-hadoop:2.6.3
LABEL authors="CHAM <CH@it_soft.ru>"
ARG target_file_name
ARG USER_INSTALL_NAME="tmdb_user"
ARG USER_INSTALL_HOME="/home/${USER_INSTALL_NAME}"
RUN useradd -u 5000 -d ${USER_INSTALL_HOME} -s /bin/bash -m ${USER_INSTALL_NAME}
RUN env
ENV TARGET_JAR=${target_file_name}.jar
ENV PROJECT_NAME="tmdb_movies"
ENV PROJECT_DIR "$USER_INSTALL_HOME/Tasks"
RUN mkdir ${PROJECT_DIR}
#copy
COPY oozie ${PROJECT_DIR}/oozie
COPY target/${target_file_name}.jar ${PROJECT_DIR}/oozie/${PROJECT_NAME}/lib/${target_file_name}.jar
RUN cp /etc/hive/conf/hive-site.xml ${PROJECT_DIR}/oozie/${PROJECT_NAME}
RUN chown -R $USER_INSTALL_NAME ${PROJECT_DIR}
RUN chmod -R 777 ${PROJECT_DIR}
USER ${USER_INSTALL_NAME}
WORKDIR ${PROJECT_DIR}/oozie/${PROJECT_NAME}
ENV USER ${USER_INSTALL_NAME}
ENV HOME ${USER_INSTALL_HOME}
RUN ls -R ${PROJECT_DIR}/oozie/${PROJECT_NAME}
RUN echo target_jar: ${TARGET_JAR}, project_dir: ${PROJECT_DIR}
CMD kinit -kt $HOME/$USER.keytab $USER && ./start_task.sh