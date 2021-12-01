# Supernova
自定义控件合集

## 1.文字搜索高亮
#### 原理：
通过继承ReplacementSpan实现自定义的Span类，在Span类中用paint分别绘制文字背景色块和文字实现<br>
#### 使用方法：
```
StringUtil.highlightKeyWordWithBackground(
                    binding.tvRoundBackgroundColorSpan,
                    "#EBF9F6",
                    "#00B38A",
                    binding.etRoundBackgroundColorSpan.text.toString()
                )
```
