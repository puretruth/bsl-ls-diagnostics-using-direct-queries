package com.github._1c_syntax.bsl.languageserver.diagnostics;

import com.github._1c_syntax.bsl.languageserver.diagnostics.metadata.*;
import com.github._1c_syntax.utils.CaseInsensitivePattern;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@DiagnosticMetadata(
  type = DiagnosticType.CODE_SMELL,
  severity = DiagnosticSeverity.CRITICAL,
  minutesToFix = 15,
  tags = {
    DiagnosticTag.DESIGN,
    DiagnosticTag.BADPRACTICE
  }
)
public class UsingDirectQueryDiagnostic extends AbstractDiagnostic {

  private static final String STOP_WORDS_DEFAULT = "";

  @DiagnosticParameter(
          type = String.class,
          defaultValue = STOP_WORDS_DEFAULT
  )
  private Pattern stopWords = CaseInsensitivePattern.compile(STOP_WORDS_DEFAULT);

  @Override
  public void configure(Map<String, Object> configuration) {
    this.stopWords = CaseInsensitivePattern.compile(
            (String) configuration.getOrDefault("stopWords", STOP_WORDS_DEFAULT));
  }

  @Override
  protected void check() {

    if (stopWords.pattern().isBlank()) {
      return;
    }

    String[] moduleLines = documentContext.getContentList();
    for (int i = 0; i < moduleLines.length; i++) {
      Matcher matcher = stopWords.matcher(moduleLines[i]);
      while (matcher.find()) {
        diagnosticStorage.addDiagnostic(i, matcher.start(), i, matcher.end());
      }
    }
  }
}
