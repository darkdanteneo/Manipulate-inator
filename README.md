Allow anyone to change attendance

Uiit attendance system uses POST requests to mark and modify attendance, the requests are sent in plain text. To authenticate the user, a static key is used which can easily be obtained by packet sniffing.

This script was last tested in March 2020. In future if this script stops working, then all one has to do is change the subject code in "strings.xml" file. Subject code can be obtained by packet sniffing a teacher or a student.

I didn't bother with network exceptions and msking the UI prettier.
