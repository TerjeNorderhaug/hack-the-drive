# Hack the Drive Demo

A demo for BMW Hack the Drive
http://docs.hackthedrive.com/

## Usage

Assuming you have leiningen installed, download the repository:

    $ git clone https://github.com/TerjeNorderhaug/hack-the-drive.git
    $ cd hack-the-drive

Open a local server:

    $ lein ring server

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

Distributed under the BSD 2-Clause License, or alternatively, under the Eclipse Public License either version 1.0 or (at
your option) any later version.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, 
THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS 
BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE 
GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, 
STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY 
OF SUCH DAMAGE.

