package model;

import java.util.HashSet;

import model.db.NewsDAO;

public class NewsManager {

private HashSet<News> allNews;
	
	
	private static NewsManager instance;
	
	private NewsManager(){
		allNews = new HashSet<News>();
		for(News m : NewsDAO.getInstance().getAllNews()){
			allNews.add(m);
		}
	}
	
	public synchronized static NewsManager getInstance(){
		if(instance == null){
			instance = new NewsManager();
		}
		return instance;
	}
	/*
	 * Accessible only for Admin (method addNews(...))
	 */
	public synchronized void makeNews(String title, String text, String category, String picturesURL, String videoURL){
		News n = new News(title, text, category, picturesURL, videoURL);
		allNews.add(n);
		NewsDAO.getInstance().addNews(n);
	}
	
	 public synchronized HashSet<News> searchNewsByTitle(String title){
		 HashSet<News> searchResult = new HashSet<News>();
			for (News news : allNews) {
				if(news.getTitle().contains(title) || title.contains(news.getTitle())){
					searchResult.add(news);
				}
			}
			return searchResult;
    }
	 
	 public synchronized HashSet<News> searchNewsByCategory(String category){
		 HashSet<News> searchResult = new HashSet<News>();
			for (News news : allNews) {
				if(news.getCategory().equalsIgnoreCase(category)){
					searchResult.add(news);
				}
			}
			return searchResult;
    }

}
