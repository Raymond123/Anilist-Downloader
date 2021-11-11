import requests
from bs4 import BeautifulSoup


class Title:
    def __init__(self, title, links):
        self.title = title
        self.links = links

    def getTitle(self):
        return self.title

    def getLinks(self):
        return self.links


titleList = list()
with open("bin/titles.md") as fp:
    for title in fp:
        urlTitle = title.replace(" ", "+");
        # print(urlTitle);
        searchUrlTest = "https://nyaa.si/?f=0&c=0_0&q=subsplease+1080+" + urlTitle
        try:
            searchPage = requests.get(searchUrlTest)
            searchSoup = BeautifulSoup(searchPage.content, 'html.parser')
            search = searchSoup.find_all('i', class_="fa-magnet")

            links = list()
            for searchResults in search:
                temp = searchResults.parent['href']
                #print(temp.replace("batc", " "))
                if "Batch" not in temp.replace("%", " "):
                    links.append(searchResults.parent['href'])

        except Exception as ex:
            print("Error: " + format(ex))
            pass
        titleList.append(Title(title, links))

wf = open("bin/d-links.md", "w")

for t in titleList:

    wf.write(t.getTitle() + ": \n")
    for lnk in t.links:
        print(lnk)
        wf.write(lnk + "\n")
    wf.write("\n")

wf.close()
