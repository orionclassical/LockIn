# LockIn
A productivity application designed specifically for students to help them organize and prioritize tasks, such as schoolwork, projects, and deadlines, such as exam preparation schedules. The app allows users to log in, record their tasks with deadlines, view them in a calendar format, and track their study progress through analytics.

# HOW TO USE GITHUB

Setup name and email for credit and identification of what each user did in the history
- git config --global user.name "{name}"
- git config -- global user.email "{email}"

Initialize Git (Don't do this when you have already cloned Git)
- git Init -> Initialize the Git

To clone the repository, you need to use this command in the terminal of VSCode
- git clone https://github.com/orionclassical/ProjectPractice.git

Check the status of your git
- git status

Adding a file for your next commit or when you're modifying
- git add {file}
- git add {file1} {file2} {./path/{file3}}

Unstaging a file when retaining the changes in the working directory
- git reset {file}

To create a new branch and traverse branches
- git checkout -b (short for branch)
- git checkout (This traverses)

To merge branches
- git merge {branch}

Committing your staged content or modified file before pushing to GitHub
- git commit -m "{descriptive message}"

Pushing your files to the remote repository at GitHub
- git push -u origin main

Getting certain files
- git fetch origin
- git checkout origin/main -- {file1} {filepath/file2}

Remembering Credentials (since every time you push, git will ask for your credentials)
- git config --global credential.helper 'cache -- timeout={seconds}

Replace your local file with the file with the new changes
- git fetch origin
- git diff origin/branch-name -- path/to/your/file
