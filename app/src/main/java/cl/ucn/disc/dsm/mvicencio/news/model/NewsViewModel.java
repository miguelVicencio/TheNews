/*
 *
 * Copyright <2021> <Miguel Vicencio Gomez>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
 * associated documentation files (the "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies
 *  of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following
 *   conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial
 * portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED,
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
 * PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT
 * OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 * OTHER DEALINGS IN THE SOFTWARE.
 */

package cl.ucn.disc.dsm.mvicencio.news.model;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import cl.ucn.disc.dsm.mvicencio.news.services.Contracts;
import cl.ucn.disc.dsm.mvicencio.news.services.newsapi.NewsAPIService;
import java.util.List;
import java.util.concurrent.Executors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class NewsViewModel extends AndroidViewModel {
  /**
   * The Logger
   */
  private static final Logger log = LoggerFactory.getLogger(NewsViewModel.class);

  /**
   * The Contract.
   */
  private final Contracts contracts = new NewsAPIService(); //ContractsImplFaker();

  /**
   * The List Of News.
   */
  private final MutableLiveData<List<News>> theNews;

  /**
   * The Constructor.
   * @param application to use.
   */
  public NewsViewModel(final Application application) {
    super(application);

    this.theNews = new MutableLiveData<>();
  }

  /**
   *
   * @return the list of news wrapped
   */
  public LiveData<List<News>> getNews(){

    return this.theNews;
  }

  /**
   * Refresh (get) the news from the backend
   */
  public void refresh(){

    //Show message if theNews are empty
    if(this.theNews.getValue() == null || this.theNews.getValue().size() == 0){
      log.warn("No News, fetching from contracts ..");
    }

    // Background thread

    Executors.newSingleThreadExecutor().execute(() ->{
      List<News> news = this.contracts.retrieveNews(50);

      //GUI thread
      new Handler(Looper.getMainLooper()).post(() -> this.theNews.setValue(news));

    });

  }

}