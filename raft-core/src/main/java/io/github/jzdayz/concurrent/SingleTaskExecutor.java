package io.github.jzdayz.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

public class SingleTaskExecutor implements TaskExecutor {

  private ExecutorService executorService;

  public SingleTaskExecutor(String name) {
    this((r) -> new Thread(name));
  }

  public SingleTaskExecutor(ThreadFactory tf) {
    executorService = Executors.newSingleThreadExecutor(tf);
  }

  @Override
  public Future<?> submit(Runnable task) {
    return executorService.submit(task);
  }

  @Override
  public <V> Future<V> submit(Callable<V> task) {
    return executorService.submit(task);
  }

  @Override
  public void shutdown() throws InterruptedException {
    executorService.shutdown();
    //noinspection ResultOfMethodCallIgnored
    executorService.awaitTermination(1L, TimeUnit.SECONDS);
  }
}
