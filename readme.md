# Setup on MAC

## Setup NODE 10.16

brew uninstall node
brew install nvm
mkdir ~/.nvm

-- Add to the ~/.bashrc:

#Node
export NVM_DIR="$HOME/.nvm"
[ -s "/usr/local/opt/nvm/nvm.sh" ] && . "/usr/local/opt/nvm/nvm.sh"  # This loads nvm

-- Restart your bash
nvm install 10.16.0
npm install -g yarn

