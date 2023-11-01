class pipelineVars implements Serializable {
    String githubRepositoryOrg = "apache"
    String githubRepositoryName = "incubator-kie-tools-temporary-rnd-do-not-use"
    String githubRepositorySlug = "apache/incubator-kie-tools-temporary-rnd-do-not-use"

    String quayPushCredentialsId = "quay-io-kie-tools-token"
    String openshiftCredentialsId = "openshift-kie-tools-token"
    String kieToolsBotGithubCredentialsId = "kie-tools-bot-gh"
    String kieToolsBotGithubTokenCredentialsID = "kie-tools-bot-gh-token"

    String defaultArtifactsTempDir = 'artifacts-tmp'
}
