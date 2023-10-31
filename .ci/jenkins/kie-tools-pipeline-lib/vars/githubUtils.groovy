/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
* Create a Github release
*
* @return String with the release information
*/
def createRelease(String repository, String name, String tag, String commit, Boolean draft, Boolean preRelease, String credentialsId) {
    withCredentials([string(credentialsId: credentialsId, variable: 'GITHUB_TOKEN')]) {
        response = sh returnStdout: true, script: """
        set +x
        curl -L \
        -X POST \
        -H "Accept: application/vnd.github+json" \
        -H "Authorization: Bearer ${GITHUB_TOKEN}" \
        -H "X-GitHub-Api-Version: 2022-11-28" \
        https://api.github.com/repos/${repository}/releases \
        -d '{"tag_name": "${tag}", "target_commitish": "${commit}", "name": "${name}", "draft": ${draft}, "prerelease": ${preRelease}'
        """.trim()

        return response
    }
}

/**
* Upload an asset to a GitHub release
*
* @return String with the release asset information
*/
def uploadReleaseAsset(String uploadUrl, String assetPath, String assetName, String assetContentType, String credentialsId) {
    withCredentials([string(credentialsId: credentialsId, variable: 'GITHUB_TOKEN')]) {
        response = sh returnStdout: true, script: """
        set +x
        curl -L \
        -X POST \
        -H "Accept: application/vnd.github+json" \
        -H "Authorization: Bearer ${GITHUB_TOKEN}" \
        -H "X-GitHub-Api-Version: 2022-11-28" \
        -H "Content-Type: ${assetContentType}" \
        "${uploadUrl}=${assetName}" \
        --data-binary "@${assetPath}"
        """.trim()

        return response
    }
}

/**
* Checkout a github repository using GitSCM class
*/
def checkoutRepo(String url, String branch, String credentialsId) {
    checkout([$class: 'GitSCM',
        branches: [[name: "${branch}"]],
        doGenerateSubmoduleConfigurations: false,
        extensions: [[$class: 'CleanCheckout']],
        submoduleCfg: [],
        userRemoteConfigs: [[credentialsId: credentialsId, url: "${url}"]]
    ])
}

/**
* Perform a squashed merge on a local repository
*/
def squashedMerge(String author, String branch, String repository) {
    sh """#!/bin/bash -el
    git config --global user.email "kietoolsbot@gmail.com"
    git config --global user.name "KIE Tools Bot (kiegroup)"
    git remote add ${author} https://github.com/${repository}.git
    git fetch ${author} ${branch}
    git merge --squash ${author}/${branch}
    git commit --no-edit
    """.trim()
}

/**
* @return the Github repository slug (org/repo) from an URL
*/
def getRepoSlug(String url) {
    tokens = url.tokenize('/')
    org = tokens[tokens.size()-4]
    repo = tokens[tokens.size()-3]

    return "${org}/${repo}"
}
