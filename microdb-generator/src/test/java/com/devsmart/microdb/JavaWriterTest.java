package com.devsmart.microdb;


import com.devsmart.microdb.ast.Nodes;
import com.google.common.io.Resources;
import com.google.testing.compile.JavaFileObjects;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.junit.Test;

import javax.tools.JavaFileObject;

import java.io.IOException;
import java.io.InputStream;

import static com.google.testing.compile.JavaSourceSubjectFactory.javaSource;
import static com.google.common.truth.Truth.assertAbout;

public class JavaWriterTest {

    private JavaFileObject myDBObjJavaSource = JavaFileObjects.forResource("org/example/MyDBObj.java");
    private JavaFileObject myExtendObjJavaSource = JavaFileObjects.forResource("org/example/ExtendObj.java");

    private static String generateJava(InputStream dboIn, int dboindex) throws IOException {
        CompilerContext compilerContext = new CompilerContext();

        ANTLRInputStream inputStream = new ANTLRInputStream(dboIn);
        MicroDBLexer lexer = new MicroDBLexer(inputStream);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        MicroDBParser parser = new MicroDBParser(tokens);

        MicroDBParser.FileContext root = parser.file();

        SemPass1 semPass1 = new SemPass1(compilerContext);
        Nodes.FileNode file = (Nodes.FileNode) semPass1.visitFile(root);
        SemPass2 semPass2 = new SemPass2(compilerContext);
        semPass2.visit(root);



        JavaCodeGenerator generator = new JavaCodeGenerator(file.dboList.get(dboindex), file);

        //StringBuffer sourceBuf = new StringBuffer();
        //generator.createJavaFile().writeTo(sourceBuf);
        //return sourceBuf.toString();

        return generator.generateCode();
    }

    @Test
    public void expectedMyDBOBJSourceCompiles() {
        assertAbout(javaSource())
                .that(myDBObjJavaSource)
                .compilesWithoutError();
    }

    @Test
    public void expectedExtendObJSourceCompiles() {
        assertAbout(javaSource())
                .that(myExtendObjJavaSource)
                .compilesWithoutError();
    }

    @Test
    public void generatedMyDBObjSourceMatchesExpected() throws Exception {

        InputStream dboIn = Resources.getResource("org/example/test.dbo").openStream();
        String javaSource = generateJava(dboIn, 0);
        dboIn.close();

        JavaFileObject generatedSource = JavaFileObjects.forSourceString("org/example/MyDBObj.java", javaSource);

        assertAbout(javaSource())
                .that(generatedSource)
                .parsesAs(myDBObjJavaSource);
    }

    @Test
    public void generatedExtendObjSourceMatchesExpected() throws Exception {
        InputStream dboIn = Resources.getResource("org/example/test.dbo").openStream();
        String javaSource = generateJava(dboIn, 1);
        dboIn.close();

        JavaFileObject generatedSource = JavaFileObjects.forSourceString("org/example/ExtendDBObj.java", javaSource);

        assertAbout(javaSource())
                .that(generatedSource)
                .parsesAs(myExtendObjJavaSource);
    }
}
