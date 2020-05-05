package org.apache.lucene.analysis.ko;

import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

import org.apache.lucene.analysis.CharArraySet;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.ko.KoreanTokenizer.DecompoundMode;
import org.apache.lucene.analysis.ko.dict.UserDictionary;
import org.apache.lucene.analysis.ko.tokenattributes.PartOfSpeechAttribute;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.OffsetAttribute;
import org.apache.lucene.analysis.tokenattributes.PositionIncrementAttribute;
import org.apache.lucene.analysis.tokenattributes.PositionLengthAttribute;

import junit.framework.TestCase;

public class TestMoprhDisplay extends TestCase {

	public void testDisplay() throws Exception {
		String text = "과학원";
		
		Set<POS.Tag> stopTags = new HashSet<POS.Tag>();
		CharArraySet stopWords = new CharArraySet(16, true);
	
		InputStreamReader streamReader = new InputStreamReader(TestMoprhDisplay.class.getResourceAsStream("userdict.txt"));
		UserDictionary userDic = UserDictionary.open(streamReader);
		KoreanAnalyzer kaa = new KoreanAnalyzer(userDic, DecompoundMode.MIXED, stopTags, false);

	  
	  TokenStream ts = kaa.tokenStream("bogus", text);
	  
	  CharTermAttribute termAtt = ts.addAttribute(CharTermAttribute.class);
	  OffsetAttribute offsetAtt = ts.addAttribute(OffsetAttribute.class);
	  PositionIncrementAttribute posIncrAtt = ts.addAttribute(PositionIncrementAttribute.class);
	  PositionLengthAttribute posLen = ts.addAttribute(PositionLengthAttribute.class);
	  PartOfSpeechAttribute morphAtt = ts.addAttribute(PartOfSpeechAttribute.class);
	     
	  ts.reset();
	  
	  int preOffset = 0;
	  String prePos = null;
	  StringBuffer actual = new StringBuffer();
	  while (ts.incrementToken()) {
			actual.
			append(termAtt.toString()).
			append("/").
			append(morphAtt.getLeftPOS().name())
			.append(",")
			.append(offsetAtt.startOffset())
			.append("-")
			.append(offsetAtt.endOffset())
			.append(",")
			.append(posIncrAtt.getPositionIncrement()).append(",")
			.append(posLen.getPositionLength())
			.append("\r\n");
		
		preOffset = offsetAtt.endOffset();
		prePos = morphAtt.getPOSType().name();
	  }
	  ts.end();
	  ts.close(); 
	      
	  System.out.print(actual);
	}
}
