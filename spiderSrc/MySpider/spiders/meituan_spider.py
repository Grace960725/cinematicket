import scrapy
from MySpider.items import MeituanFilm
from MySpider.items import MeituanFilmScreenings

class MeituanSpider(scrapy.Spider):
    name = 'meituan'
    start_urls = []
    price_num_dict = {' -14px -26px': '2', ' -46px -26px': '8'}
    user_agent = 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36'

    def start_requests(self):
        request_url = 'http://nj.meituan.com/dianying/'
        return [scrapy.FormRequest(request_url, callback=self.parse_movie)]

    def parse_movie(self, response):
        movie_list = response.xpath('//div[@class="reco-movieinfo"] | //div[@class="reco-movieinfo reco-movieinfo--last"]')
        for index, div in enumerate(movie_list):
            movie_name = div.xpath('.//h3/text()').extract_first()
            movie_url = div.xpath('./a/@href').extract_first()
            next_request = movie_url + '?mtt=1.movie%2Fmoviedeal'
            yield scrapy.Request(next_request, callback=self.parse_date)

    # def parse_cinema(self, response):
    #     cinema_list = response.xpath('//div[@data-component="cinema-list"]//textarea//a[@class="link--black__green"]/@href').extract()
    #     items = []
    #     for cinema_url in cinema_list:
    #         result = scrapy.Request('http:'+cinema_url, callback=self.parse_cinema_detail)
    #         items.append(result)
    #     next_page_url = response.xpath('//ul[@class="paginator paginator--notri paginator--large"]//a[@gaevent="content/page/next"]//@href').extract_first()
    #     if next_page_url!=None:
    #         result = scrapy.Request(next_page_url, callback=self.parse_cinema)
    #         items += result
    #     print(items)
    #     return items

    def parse_date(self, response):
        dates = response.xpath('//ul[@class="inline-block-list"]')
        for index, item in enumerate(dates):
            if index==0:
                hrefs = item.xpath('./li/a/@href').extract()
                for a in hrefs:
                    yield scrapy.Request(a, callback=self.parse_cinema)

    def parse_cinema(self, response):
        page_urls = []
        page_urls.append(response.url+'?mtt=1.movie%2Fmoviedetail')
        pages = response.xpath('//ul[@class="paginator paginator--notri paginator--large"]//a')
        for index, a in enumerate(pages):
            text = a.xpath('./text()').extract_first()
            if(len(text)==1 and text!='1'):
                page_urls.append(a.xpath('./@href').extract_first())
        for page_url in page_urls:
            yield scrapy.Request(page_url, callback=self.parse_cinema_page)

    def parse_cinema_page(self, response):
        cinema_list = response.xpath('//div[@data-component="cinema-list"]//textarea//a[@class="link--black__green"]/@href').extract()
        for cinema_url in cinema_list:
            yield scrapy.Request('http:'+cinema_url, headers={'User-Agent': self.user_agent},
                                 callback=self.parse_cinema_detail)

    def parse_cinema_detail(self, response):
        items = []
        url = response.url
        movie_id = url[url.find('=')+1: url.find('&')]
        url_date = url[url.find('date=')+5: ]
        cinema_name = response.xpath('//div[@class="summary biz-box"]/h2/text()').extract_first()
        cinema_name = cinema_name.replace('\n', '')
        detail_info = response.xpath('//div[@class="movie-info"]')
        for mi, info in enumerate(detail_info):
            movie_href = info.xpath('.//a[@class="movie-info__name"]/@href').extract_first()
            movie_href = movie_href[7: ]
            if movie_href!=movie_id:
                continue
            movie_name = info.xpath('.//a[@class="movie-info__name"]/h3/text()').extract_first()
            date_list = info.xpath('.//div[@class="show-time"]/a/@data-date').extract()
            # print(date_list)
            table_list = info.xpath('.//table')
            for index, table in enumerate(table_list):
                date = date_list[index]
                # print(url_date+' '+date)
                if url_date==date:
                    screenings = []
                    if len(table.xpath('.//tr[2]/td').extract())==1:
                        continue
                    rows = table.xpath('.//tr')
                    for j, row in enumerate(rows):
                        if j!=0:
                            time = row.xpath('.//span[@class="start-time"]/text()').extract_first()
                            auditorium = row.xpath('./td/text()').extract()[2]
                            type = row.xpath('./td/text()').extract()[1]
                            screening = MeituanFilmScreenings(auditorium=auditorium, time=time, type=type)
                            screenings.append(screening)
                    film = MeituanFilm(name=movie_name.strip(), cinema=cinema_name.strip(), date=date, screenings=screenings)
                    items.append(film)
            return items