/* Generated By:JavaCC: Do not edit this line. NodeParser.java */
/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.ontology2.rdf.parser;

import com.hp.hpl.jena.n3.turtle.ParserBase ;
import com.hp.hpl.jena.graph.* ;



public class NodeParser extends ParserBase implements NodeParserConstants {
    private Node nodeValue;
    public Node getNodeValue() {
        return nodeValue;
    }

// --- Entry point
  final public void parse() throws ParseException {
    GraphTerm();
    jj_consume_token(0);
  }

  final public void GraphTerm() throws ParseException {
                     String iri ;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case IRIref:
    case PNAME_NS:
    case PNAME_LN:
      iri = IRIref();
                      nodeValue=createNode(iri) ;
      break;
    case STRING_LITERAL1:
    case STRING_LITERAL2:
    case STRING_LITERAL_LONG1:
    case STRING_LITERAL_LONG2:
      nodeValue = RDFLiteral();
      break;
    case INTEGER:
    case DECIMAL:
    case DOUBLE:
      // Cleaner sign handling in Turtle.
        nodeValue = NumericLiteral();
      break;
    case TRUE:
    case FALSE:
      nodeValue = BooleanLiteral();
      break;
    case BLANK_NODE_LABEL:
    case ANON:
      nodeValue = BlankNode();
      break;
    case NIL:
      jj_consume_token(NIL);
           nodeValue=nRDFnil ;
      break;
    default:
      jj_la1[0] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

// ---- Basic terms
  final public Node NumericLiteral() throws ParseException {
                          Token t ;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case INTEGER:
      t = jj_consume_token(INTEGER);
                  {if (true) return createLiteralInteger(t.image) ;}
      break;
    case DECIMAL:
      t = jj_consume_token(DECIMAL);
                  {if (true) return createLiteralDecimal(t.image) ;}
      break;
    case DOUBLE:
      t = jj_consume_token(DOUBLE);
                 {if (true) return createLiteralDouble(t.image) ;}
      break;
    default:
      jj_la1[1] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

  final public Node RDFLiteral() throws ParseException {
                      Token t ; String lex = null ;
    lex = String();
    String lang = null ; String dt = null ;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case PREFIX:
    case BASE:
    case LANGTAG:
    case DATATYPE:
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case PREFIX:
      case BASE:
      case LANGTAG:
        lang = Langtag();
        break;
      case DATATYPE:
        jj_consume_token(DATATYPE);
        dt = IRIref();
        break;
      default:
        jj_la1[2] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      break;
    default:
      jj_la1[3] = jj_gen;
      ;
    }
      {if (true) return createLiteral(lex, lang, dt) ;}
    throw new Error("Missing return statement in function");
  }

  final public String Langtag() throws ParseException {
                     Token t ;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case LANGTAG:
      t = jj_consume_token(LANGTAG);
      break;
    case PREFIX:
    case BASE:
      t = AnyDirective();
      break;
    default:
      jj_la1[4] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    String lang = stripChars(t.image, 1) ; {if (true) return lang ;}
    throw new Error("Missing return statement in function");
  }

  final public Token AnyDirective() throws ParseException {
                         Token t ;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case PREFIX:
      t = jj_consume_token(PREFIX);
      break;
    case BASE:
      t = jj_consume_token(BASE);
      break;
    default:
      jj_la1[5] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
                                    {if (true) return t ;}
    throw new Error("Missing return statement in function");
  }

  final public Node BooleanLiteral() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case TRUE:
      jj_consume_token(TRUE);
            {if (true) return XSD_TRUE ;}
      break;
    case FALSE:
      jj_consume_token(FALSE);
             {if (true) return XSD_FALSE ;}
      break;
    default:
      jj_la1[6] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

  final public String String() throws ParseException {
                    Token t ;  String lex ;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case STRING_LITERAL1:
      t = jj_consume_token(STRING_LITERAL1);
                            lex = stripQuotes(t.image) ;
      break;
    case STRING_LITERAL2:
      t = jj_consume_token(STRING_LITERAL2);
                            lex = stripQuotes(t.image) ;
      break;
    case STRING_LITERAL_LONG1:
      t = jj_consume_token(STRING_LITERAL_LONG1);
                                 lex = stripQuotes3(t.image) ;
      break;
    case STRING_LITERAL_LONG2:
      t = jj_consume_token(STRING_LITERAL_LONG2);
                                 lex = stripQuotes3(t.image) ;
      break;
    default:
      jj_la1[7] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
      lex = unescapeStr(lex,  t.beginLine, t.beginColumn) ;
      {if (true) return lex ;}
    throw new Error("Missing return statement in function");
  }

  final public String IRIref() throws ParseException {
                    String iri ;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case IRIref:
      iri = IRI_REF();
                    {if (true) return iri ;}
      break;
    case PNAME_NS:
    case PNAME_LN:
      iri = PrefixedName();
                         {if (true) return iri ;}
      break;
    default:
      jj_la1[8] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

  final public String PrefixedName() throws ParseException {
                          Token t ;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case PNAME_LN:
      t = jj_consume_token(PNAME_LN);
      {if (true) return resolvePName(t.image, t.beginLine, t.beginColumn) ;}
      break;
    case PNAME_NS:
      t = jj_consume_token(PNAME_NS);
      {if (true) return resolvePName(t.image, t.beginLine, t.beginColumn) ;}
      break;
    default:
      jj_la1[9] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

  final public Node BlankNode() throws ParseException {
                      Token t = null ;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case BLANK_NODE_LABEL:
      t = jj_consume_token(BLANK_NODE_LABEL);
      {if (true) return createBNode(t.image, t.beginLine, t.beginColumn) ;}
      break;
    case ANON:
      jj_consume_token(ANON);
           {if (true) return createBNode() ;}
      break;
    default:
      jj_la1[10] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

  final public String IRI_REF() throws ParseException {
                     Token t ;
    t = jj_consume_token(IRIref);
    {if (true) return resolveQuotedIRI(t.image, t.beginLine, t.beginColumn) ;}
    throw new Error("Missing return statement in function");
  }

  /** Generated Token Manager. */
  public NodeParserTokenManager token_source;
  JavaCharStream jj_input_stream;
  /** Current token. */
  public Token token;
  /** Next token. */
  public Token jj_nt;
  private int jj_ntk;
  private int jj_gen;
  final private int[] jj_la1 = new int[11];
  static private int[] jj_la1_0;
  static private int[] jj_la1_1;
  static {
      jj_la1_init_0();
      jj_la1_init_1();
   }
   private static void jj_la1_init_0() {
      jj_la1_0 = new int[] {0x1ef0f800,0xe000,0x40000600,0x40000600,0x40000600,0x600,0x1800,0xf00000,0xe000000,0xc000000,0x10000000,};
   }
   private static void jj_la1_init_1() {
      jj_la1_1 = new int[] {0x108,0x0,0x400000,0x400000,0x0,0x0,0x0,0x0,0x0,0x0,0x100,};
   }

  /** Constructor with InputStream. */
  public NodeParser(java.io.InputStream stream) {
     this(stream, null);
  }
  /** Constructor with InputStream and supplied encoding */
  public NodeParser(java.io.InputStream stream, String encoding) {
    try { jj_input_stream = new JavaCharStream(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source = new NodeParserTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 11; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(java.io.InputStream stream) {
     ReInit(stream, null);
  }
  /** Reinitialise. */
  public void ReInit(java.io.InputStream stream, String encoding) {
    try { jj_input_stream.ReInit(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 11; i++) jj_la1[i] = -1;
  }

  /** Constructor. */
  public NodeParser(java.io.Reader stream) {
    jj_input_stream = new JavaCharStream(stream, 1, 1);
    token_source = new NodeParserTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 11; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(java.io.Reader stream) {
    jj_input_stream.ReInit(stream, 1, 1);
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 11; i++) jj_la1[i] = -1;
  }

  /** Constructor with generated Token Manager. */
  public NodeParser(NodeParserTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 11; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(NodeParserTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 11; i++) jj_la1[i] = -1;
  }

  private Token jj_consume_token(int kind) throws ParseException {
    Token oldToken;
    if ((oldToken = token).next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    if (token.kind == kind) {
      jj_gen++;
      return token;
    }
    token = oldToken;
    jj_kind = kind;
    throw generateParseException();
  }


/** Get the next Token. */
  final public Token getNextToken() {
    if (token.next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    jj_gen++;
    return token;
  }

/** Get the specific Token. */
  final public Token getToken(int index) {
    Token t = token;
    for (int i = 0; i < index; i++) {
      if (t.next != null) t = t.next;
      else t = t.next = token_source.getNextToken();
    }
    return t;
  }

  private int jj_ntk() {
    if ((jj_nt=token.next) == null)
      return (jj_ntk = (token.next=token_source.getNextToken()).kind);
    else
      return (jj_ntk = jj_nt.kind);
  }

  private java.util.List<int[]> jj_expentries = new java.util.ArrayList<int[]>();
  private int[] jj_expentry;
  private int jj_kind = -1;

  /** Generate ParseException. */
  public ParseException generateParseException() {
    jj_expentries.clear();
    boolean[] la1tokens = new boolean[63];
    if (jj_kind >= 0) {
      la1tokens[jj_kind] = true;
      jj_kind = -1;
    }
    for (int i = 0; i < 11; i++) {
      if (jj_la1[i] == jj_gen) {
        for (int j = 0; j < 32; j++) {
          if ((jj_la1_0[i] & (1<<j)) != 0) {
            la1tokens[j] = true;
          }
          if ((jj_la1_1[i] & (1<<j)) != 0) {
            la1tokens[32+j] = true;
          }
        }
      }
    }
    for (int i = 0; i < 63; i++) {
      if (la1tokens[i]) {
        jj_expentry = new int[1];
        jj_expentry[0] = i;
        jj_expentries.add(jj_expentry);
      }
    }
    int[][] exptokseq = new int[jj_expentries.size()][];
    for (int i = 0; i < jj_expentries.size(); i++) {
      exptokseq[i] = jj_expentries.get(i);
    }
    return new ParseException(token, exptokseq, tokenImage);
  }

  /** Enable tracing. */
  final public void enable_tracing() {
  }

  /** Disable tracing. */
  final public void disable_tracing() {
  }

}
