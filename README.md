# Hack the Drive Demo

A demo for BMW Hack the Drive
http://docs.hackthedrive.com/

## Usage


## Quick Start

Our Hack The Drive demo can run on the Heroku PAAS http://heroku.com.
Assuming you have an account on Heroku.com and have installed the Heroku Toolbelt on your workstation, 
start by cloning the repo:

    $ git clone git@github.com:TerjeNorderhaug/hack-the-drive.git
    $ cd hack-the-drive

Create the app by executing the following in a shell with the hack-the-drive root directory as current path:

    $ heroku apps:create

Add a free MongoDB instance:

    $ heroku addons:add mongolab

Deploy the code:

    $ git push heroku master

Open the webpage:

    # heroku open

## License

Copyright © 2015 Terje Norderhaug <terje@in-progress.com>

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
