//package trie;
//
//import java.util.Map;
//import java.util.Random;
//import java.util.concurrent.ConcurrentHashMap;
//
//
//public class TrieMapPerformanceTest {
//    public static void main(String[] args) throws InterruptedException {
//        int sum = 0;
//        for(int i = 0; i < 1; i ++) {
//            sum += call();
//        }
//        System.out.println("Avarage:" + (sum/1));
//    }
//    public static long call() throws InterruptedException {
//        int [] i = new int[0];
//        long end = 0;
//        long start = 0;
//
//
//        //////////////////////////////////////////////////////////////
//        //////////////////////////////////////////////////////////////
//        ////// IMPORTANT: TEST MAP and TRIE separately
//        //////            Else successive run may utilize already created
//        //////            Strings and performs better.
//        //////////////////////////////////////////////////////////////
//        //////////////////////////////////////////////////////////////
//
//        //		Thread.sleep(11000);
//		/*Map m = new ConcurrentHashMap();
//		start = System.currentTimeMillis();
//		CreateMapThread m1 = new CreateMapThread(m);
//		m1.start();
//		m1.join();
//
//		CreateMapThread m2 = new CreateMapThread(m);
//		CreateMapThread m3 = new CreateMapThread(m);
//		CreateMapThread m4 = new CreateMapThread(m);
//		CreateMapThread m5 = new CreateMapThread(m);
//		CreateMapThread m6 = new CreateMapThread(m);
//		ReadMapThread m7 = new ReadMapThread(m);
//		ReadMapThread m8 = new ReadMapThread(m);
//		ReadMapThread m9 = new ReadMapThread(m);
//		ReadMapThread m10 = new ReadMapThread(m);
//		ReadMapThread m11 = new ReadMapThread(m);
//		ReadMapThread m12 = new ReadMapThread(m);
//		RemoveMapThread m13 = new RemoveMapThread(m);
//		RemoveMapThread m14 = new RemoveMapThread(m);
//		RemoveMapThread m15 = new RemoveMapThread(m);
//		RemoveMapThread m16 = new RemoveMapThread(m);
//		RemoveMapThread m17 = new RemoveMapThread(m);
//		RemoveMapThread m18 = new RemoveMapThread(m);
//
//
//		CreateMapThread m19 = new CreateMapThread(m);
//		CreateMapThread m20 = new CreateMapThread(m);
//		CreateMapThread m21 = new CreateMapThread(m);
//		CreateMapThread m22 = new CreateMapThread(m);
//		CreateMapThread m23 = new CreateMapThread(m);
//		ReadMapThread m24 = new ReadMapThread(m);
//		ReadMapThread m25 = new ReadMapThread(m);
//		ReadMapThread m26 = new ReadMapThread(m);
//		ReadMapThread m27 = new ReadMapThread(m);
//		ReadMapThread m28 = new ReadMapThread(m);
//		ReadMapThread m29 = new ReadMapThread(m);
//		RemoveMapThread m30 = new RemoveMapThread(m);
//		RemoveMapThread m31 = new RemoveMapThread(m);
//		RemoveMapThread m32 = new RemoveMapThread(m);
//		RemoveMapThread m33 = new RemoveMapThread(m);
//		RemoveMapThread m34 = new RemoveMapThread(m);
//		RemoveMapThread m35 = new RemoveMapThread(m);
//
//
//		CreateMapThread m36 = new CreateMapThread(m);
//		CreateMapThread m37 = new CreateMapThread(m);
//		CreateMapThread m38 = new CreateMapThread(m);
//		CreateMapThread m39 = new CreateMapThread(m);
//		CreateMapThread m40 = new CreateMapThread(m);
//		ReadMapThread m41 = new ReadMapThread(m);
//		ReadMapThread m42 = new ReadMapThread(m);
//		ReadMapThread m43 = new ReadMapThread(m);
//		ReadMapThread m44 = new ReadMapThread(m);
//		ReadMapThread m45 = new ReadMapThread(m);
//		ReadMapThread m46 = new ReadMapThread(m);
//		RemoveMapThread m47 = new RemoveMapThread(m);
//		RemoveMapThread m48 = new RemoveMapThread(m);
//		RemoveMapThread m49 = new RemoveMapThread(m);
//		RemoveMapThread m50 = new RemoveMapThread(m);
//		RemoveMapThread m51 = new RemoveMapThread(m);
//		RemoveMapThread m52 = new RemoveMapThread(m);
//
//		CreateMapThread m53 = new CreateMapThread(m);
//		CreateMapThread m54 = new CreateMapThread(m);
//		CreateMapThread m55 = new CreateMapThread(m);
//		CreateMapThread m56 = new CreateMapThread(m);
//		CreateMapThread m57 = new CreateMapThread(m);
//		ReadMapThread m58 = new ReadMapThread(m);
//		ReadMapThread m59 = new ReadMapThread(m);
//		ReadMapThread m60 = new ReadMapThread(m);
//		ReadMapThread m61 = new ReadMapThread(m);
//		ReadMapThread m62 = new ReadMapThread(m);
//		ReadMapThread m63 = new ReadMapThread(m);
//		RemoveMapThread m64 = new RemoveMapThread(m);
//		RemoveMapThread m65 = new RemoveMapThread(m);
//		RemoveMapThread m66 = new RemoveMapThread(m);
//		RemoveMapThread m67 = new RemoveMapThread(m);
//		RemoveMapThread m68 = new RemoveMapThread(m);
//		RemoveMapThread m69 = new RemoveMapThread(m);
//
//		m2.start();
//		m3.start();
//		m4.start();
//		m5.start();
//		m6.start();
//		m7.start();
//		m8.start();
//		m9.start();
//		m10.start();
//		m11.start();
//		m12.start();
//		m13.start();
//		m14.start();
//		m15.start();
//		m16.start();
//		m17.start();
//		m18.start();
//
//		m19.start();
//		m20.start();
//		m21.start();
//		m22.start();
//		m23.start();
//		m24.start();
//		m25.start();
//		m26.start();
//		m27.start();
//		m28.start();
//		m29.start();
//		m30.start();
//		m31.start();
//		m32.start();
//		m33.start();
//		m34.start();
//		m35.start();
//
//		m36.start();
//		m37.start();
//		m38.start();
//		m39.start();
//		m40.start();
//		m41.start();
//		m42.start();
//		m43.start();
//		m44.start();
//		m45.start();
//		m46.start();
//		m47.start();
//		m48.start();
//		m49.start();
//		m50.start();
//		m51.start();
//		m52.start();
//
//		m53.start();
//		m54.start();
//		m55.start();
//		m56.start();
//		m57.start();
//		m58.start();
//		m59.start();
//		m60.start();
//		m61.start();
//		m62.start();
//		m63.start();
//		m64.start();
//		m65.start();
//		m66.start();
//		m67.start();
//		m68.start();
//		m69.start();
//
//		m2.join();
//		m3.join();
//		m4.join();
//		m5.join();
//		m6.join();
//		m7.join();
//		m8.join();
//		m9.join();
//		m10.join();
//		m11.join();
//		m12.join();
//		m13.join();
//		m14.join();
//		m15.join();
//		m16.join();
//		m17.join();
//		m18.join();
//
//		m19.join();
//		m20.join();
//		m21.join();
//		m22.join();
//		m23.join();
//		m24.join();
//		m25.join();
//		m26.join();
//		m27.join();
//		m28.join();
//		m29.join();
//		m30.join();
//		m31.join();
//		m32.join();
//		m33.join();
//		m34.join();
//		m35.join();
//
//		m36.join();
//		m37.join();
//		m38.join();
//		m39.join();
//		m40.join();
//		m41.join();
//		m42.join();
//		m43.join();
//		m44.join();
//		m45.join();
//		m46.join();
//		m47.join();
//		m48.join();
//		m49.join();
//		m50.join();
//		m51.join();
//		m52.join();
//
//		m53.join();
//		m54.join();
//		m55.join();
//		m56.join();
//		m57.join();
//		m58.join();
//		m59.join();
//		m60.join();
//		m61.join();
//		m62.join();
//		m63.join();
//		m64.join();
//		m65.join();
//		m66.join();
//		m67.join();
//		m68.join();
//		m69.join();
//
//		end = System.currentTimeMillis();
//		System.out.println("Time taken for HashMap :" + (end - start));
//		System.out.println("Size" + m.size()); */
//
//
//
//
//        //		Unmatched received. key-19996470301 created: 19996470301 received:9566963131
//        //		Unmatched received. key-15630146561 created: 15630146561 received:5200639391
//        //		Unmatched received. key-6113274431 created: 6113274431 received:16092898201
///*
// * 		String key = "6113274431";
//		Node created1 = l.createLink(key.hashCode(), 1, key, key);
//		key = "16092898201";
//		Node created2 = l.createLink(key.hashCode(), 1, key, key);
//		key = "6113274431";
//	    Node received1 = l.getLink(key, key.hashCode(), 1);
//	    key = "16092898201";
//	    Node received2 = l.getLink(key, key.hashCode(), 1);
// */
//
//
//
//        //		Base10ToBaseX.Base[] bases = {Base10ToBaseX.Base.BASE8, Base10ToBaseX.Base.BASE8, Base10ToBaseX.Base.BASE8, Base10ToBaseX.Base.BASE8, Base10ToBaseX.Base.BASE8,
//        //				                      Base10ToBaseX.Base.BASE8, Base10ToBaseX.Base.BASE4, Base10ToBaseX.Base.BASE4, Base10ToBaseX.Base.BASE4, Base10ToBaseX.Base.BASE4,
//        //				                      Base10ToBaseX.Base.BASE4, Base10ToBaseX.Base.BASE4, Base10ToBaseX.Base.BASE4, Base10ToBaseX.Base.BASE4
//        //		                             };
//        //		Base10ToBaseX.Base[] bases = { Base10ToBaseX.Base.Base65536,
//        //				Base10ToBaseX.Base.BASE8, Base10ToBaseX.Base.BASE8,
//        //				Base10ToBaseX.Base.BASE4, Base10ToBaseX.Base.BASE4,
//        //				Base10ToBaseX.Base.BASE4, Base10ToBaseX.Base.BASE4,
//        //				Base10ToBaseX.Base.BASE2, Base10ToBaseX.Base.BASE2 };
//
//        //		Base10ToBaseX.Base[] bases = { Base10ToBaseX.Base.Base65536,
//        //				Base10ToBaseX.Base.BASE256, Base10ToBaseX.Base.BASE8,
//        //				Base10ToBaseX.Base.BASE2, Base10ToBaseX.Base.BASE2,
//        //				Base10ToBaseX.Base.BASE2, Base10ToBaseX.Base.BASE2,
//        //				Base10ToBaseX.Base.BASE2, Base10ToBaseX.Base.BASE2,
//        //				Base10ToBaseX.Base.BASE2, Base10ToBaseX.Base.BASE2,
//        //				Base10ToBaseX.Base.BASE2, Base10ToBaseX.Base.BASE2,
//        //				Base10ToBaseX.Base.BASE2, Base10ToBaseX.Base.BASE2,
//        //				Base10ToBaseX.Base.BASE2, Base10ToBaseX.Base.BASE2};
//        //
//        //		Base10ToBaseX.Base[] bases = { Base10ToBaseX.Base.BASE256,
//        //				Base10ToBaseX.Base.BASE256, Base10ToBaseX.Base.BASE8,
//        //				Base10ToBaseX.Base.BASE8, Base10ToBaseX.Base.BASE4,
//        //				Base10ToBaseX.Base.BASE2, Base10ToBaseX.Base.BASE2,
//        //				Base10ToBaseX.Base.BASE2, Base10ToBaseX.Base.BASE2,
//        //				Base10ToBaseX.Base.BASE2, Base10ToBaseX.Base.BASE2,
//        //				Base10ToBaseX.Base.BASE4, Base10ToBaseX.Base.BASE4,
//        //				Base10ToBaseX.Base.BASE2, Base10ToBaseX.Base.BASE2,
//        //				Base10ToBaseX.Base.BASE2, Base10ToBaseX.Base.BASE2};
//
//        //		Base10ToBaseX.Base[] bases = { Base10ToBaseX.Base.BASE256,
//        //				Base10ToBaseX.Base.BASE256, Base10ToBaseX.Base.BASE8,
//        //				Base10ToBaseX.Base.BASE4, Base10ToBaseX.Base.BASE2,
//        //				Base10ToBaseX.Base.BASE2, Base10ToBaseX.Base.BASE2,
//        //				Base10ToBaseX.Base.BASE2, Base10ToBaseX.Base.BASE2,
//        //				Base10ToBaseX.Base.BASE2, Base10ToBaseX.Base.BASE2,
//        //				Base10ToBaseX.Base.BASE2, Base10ToBaseX.Base.BASE2,
//        //				Base10ToBaseX.Base.BASE2, Base10ToBaseX.Base.BASE2,
//        //				Base10ToBaseX.Base.BASE2, Base10ToBaseX.Base.BASE2,
//        //				Base10ToBaseX.Base.BASE2, Base10ToBaseX.Base.BASE2,
//        //				Base10ToBaseX.Base.BASE2, Base10ToBaseX.Base.BASE2,
//        //				Base10ToBaseX.Base.BASE2, Base10ToBaseX.Base.BASE2,
//        //				Base10ToBaseX.Base.BASE2, Base10ToBaseX.Base.BASE2,
//        //				Base10ToBaseX.Base.BASE2, Base10ToBaseX.Base.BASE2};
//
//        //		Thread.sleep(11000);
//        //		Edge.MAX_TREE_LEVEL = 3;
//        //		Base10ToBaseX.Base[] bases = { Base10ToBaseX.Base.Base65536,
//        //		Base10ToBaseX.Base.BASE2, Base10ToBaseX.Base.BASE4,
//        //		Base10ToBaseX.Base.BASE4, Base10ToBaseX.Base.BASE2,
//        //		Base10ToBaseX.Base.BASE2, Base10ToBaseX.Base.BASE2,
//        //		Base10ToBaseX.Base.BASE2, Base10ToBaseX.Base.BASE2,
//        //		Base10ToBaseX.Base.BASE2, Base10ToBaseX.Base.BASE2,
//        //		Base10ToBaseX.Base.BASE2, Base10ToBaseX.Base.BASE2,
//        //		Base10ToBaseX.Base.BASE2, Base10ToBaseX.Base.BASE2,
//        //		Base10ToBaseX.Base.BASE2, Base10ToBaseX.Base.BASE2 };
//
//        //		Edge.MAX_TREE_LEVEL = 7;
//        //		Base10ToBaseX.Base[] bases = { Base10ToBaseX.Base.BASE8, Base10ToBaseX.Base.BASE8,
//        //		Base10ToBaseX.Base.BASE8, Base10ToBaseX.Base.BASE8,
//        //		Base10ToBaseX.Base.BASE8, Base10ToBaseX.Base.BASE8,
//        //		Base10ToBaseX.Base.BASE2, Base10ToBaseX.Base.BASE4,
//        //		Base10ToBaseX.Base.BASE4, Base10ToBaseX.Base.BASE2,
//        //		Base10ToBaseX.Base.BASE2, Base10ToBaseX.Base.BASE2,
//        //		Base10ToBaseX.Base.BASE2, Base10ToBaseX.Base.BASE2,
//        //		Base10ToBaseX.Base.BASE2, Base10ToBaseX.Base.BASE2,
//        //		Base10ToBaseX.Base.BASE2, Base10ToBaseX.Base.BASE2,
//        //		Base10ToBaseX.Base.BASE2, Base10ToBaseX.Base.BASE2,
//        //		Base10ToBaseX.Base.BASE2, Base10ToBaseX.Base.BASE2,
//        //		Base10ToBaseX.Base.BASE2, Base10ToBaseX.Base.BASE2,
//        //		Base10ToBaseX.Base.BASE2, Base10ToBaseX.Base.BASE2,
//        //		Base10ToBaseX.Base.BASE2, Base10ToBaseX.Base.BASE2};
//
//
//
//        //One Lakh record
//        //		Edge.MAX_TREE_LEVEL = 2;
//        //		Base10ToBaseX.Base[] bases = { Base10ToBaseX.Base.Base65536, Base10ToBaseX.Base.BASE2, Base10ToBaseX.Base.BASE2 };
//
//        //		Edge.MAX_TREE_LEVEL = 3;
//        //		Base10ToBaseX.Base[] bases = { Base10ToBaseX.Base.BASE256, Base10ToBaseX.Base.BASE256, Base10ToBaseX.Base.BASE2, Base10ToBaseX.Base.BASE2, Base10ToBaseX.Base.BASE2  };
//
//        //		Edge.MAX_TREE_LEVEL = 2; //Best combination
//        //		Base10ToBaseX.Base[] bases = { Base10ToBaseX.Base.BASE256, Base10ToBaseX.Base.BASE256, Base10ToBaseX.Base.BASE2  };
//
//        //		Edge.MAX_TREE_LEVEL = 1;
//        //		Base10ToBaseX.Base[] bases = { Base10ToBaseX.Base.Base65536, Base10ToBaseX.Base.BASE2};
//
//        //		Edge.MAX_TREE_LEVEL = 4;
//        //		Base10ToBaseX.Base[] bases = { Base10ToBaseX.Base.BASE8, Base10ToBaseX.Base.BASE8,Base10ToBaseX.Base.BASE8, Base10ToBaseX.Base.BASE8, Base10ToBaseX.Base.BASE2};
//
//
//
//        //4 Lakh
//        //		Edge.MAX_TREE_LEVEL = 2;
//        //		Base10ToBaseX.Base[] bases = { Base10ToBaseX.Base.Base65536, Base10ToBaseX.Base.BASE4, Base10ToBaseX.Base.BASE2};
//
//        //		Edge.MAX_TREE_LEVEL = 3;
//        //		Base10ToBaseX.Base[] bases = { Base10ToBaseX.Base.BASE256, Base10ToBaseX.Base.BASE256, Base10ToBaseX.Base.BASE8,  Base10ToBaseX.Base.BASE2};
//
//        //		Edge.MAX_TREE_LEVEL = 4; //Best combination
//        //		Base10ToBaseX.Base[] bases = { Base10ToBaseX.Base.BASE256, Base10ToBaseX.Base.BASE8, Base10ToBaseX.Base.BASE8, Base10ToBaseX.Base.BASE8,  Base10ToBaseX.Base.BASE2};
//
//        Edge.MAX_TREE_LEVEL = 2;
//        Base10ToBaseX.Base[] bases = { Base10ToBaseX.Base.Base65536, Base10ToBaseX.Base.BASE2, Base10ToBaseX.Base.BASE2};
//
//
//        start = System.currentTimeMillis();
//        Edge.base = new VariantBases(bases);
//
//        Edge l = new Edge(bases[0].getLevelZeroMask() + 1);
//        CreateLinkThread r1 = new CreateLinkThread(l);
//        r1.start();
//        r1.join();
//        CreateLinkThread r2 = new CreateLinkThread(l);
//        CreateLinkThread r3 = new CreateLinkThread(l);
//        CreateLinkThread r4 = new CreateLinkThread(l);
//        CreateLinkThread r5 = new CreateLinkThread(l);
//        CreateLinkThread r6 = new CreateLinkThread(l);
//        ReadLinkThread r7 = new ReadLinkThread(l);
//        ReadLinkThread r8 = new ReadLinkThread(l);
//        ReadLinkThread r9 = new ReadLinkThread(l);
//        ReadLinkThread r10 = new ReadLinkThread(l);
//        ReadLinkThread r11 = new ReadLinkThread(l);
//        ReadLinkThread r12 = new ReadLinkThread(l);
//        RemoveLinkThread r13 = new RemoveLinkThread(l);
//        RemoveLinkThread r14 = new RemoveLinkThread(l);
//        RemoveLinkThread r15 = new RemoveLinkThread(l);
//        RemoveLinkThread r16 = new RemoveLinkThread(l);
//        RemoveLinkThread r17 = new RemoveLinkThread(l);
//        RemoveLinkThread r18 = new RemoveLinkThread(l);
//
//
//        CreateLinkThread r19 = new CreateLinkThread(l);
//        CreateLinkThread r20 = new CreateLinkThread(l);
//        CreateLinkThread r21 = new CreateLinkThread(l);
//        CreateLinkThread r22 = new CreateLinkThread(l);
//        CreateLinkThread r23 = new CreateLinkThread(l);
//        ReadLinkThread r24 = new ReadLinkThread(l);
//        ReadLinkThread r25 = new ReadLinkThread(l);
//        ReadLinkThread r26 = new ReadLinkThread(l);
//        ReadLinkThread r27 = new ReadLinkThread(l);
//        ReadLinkThread r28 = new ReadLinkThread(l);
//        ReadLinkThread r29 = new ReadLinkThread(l);
//        RemoveLinkThread r30 = new RemoveLinkThread(l);
//        RemoveLinkThread r31 = new RemoveLinkThread(l);
//        RemoveLinkThread r32 = new RemoveLinkThread(l);
//        RemoveLinkThread r33 = new RemoveLinkThread(l);
//        RemoveLinkThread r34 = new RemoveLinkThread(l);
//        RemoveLinkThread r35 = new RemoveLinkThread(l);
//
//        CreateLinkThread r36 = new CreateLinkThread(l);
//        CreateLinkThread r37 = new CreateLinkThread(l);
//        CreateLinkThread r38 = new CreateLinkThread(l);
//        CreateLinkThread r39 = new CreateLinkThread(l);
//        CreateLinkThread r40 = new CreateLinkThread(l);
//        ReadLinkThread r41 = new ReadLinkThread(l);
//        ReadLinkThread r42 = new ReadLinkThread(l);
//        ReadLinkThread r43 = new ReadLinkThread(l);
//        ReadLinkThread r44 = new ReadLinkThread(l);
//        ReadLinkThread r45 = new ReadLinkThread(l);
//        ReadLinkThread r46 = new ReadLinkThread(l);
//        RemoveLinkThread r47 = new RemoveLinkThread(l);
//        RemoveLinkThread r48 = new RemoveLinkThread(l);
//        RemoveLinkThread r49 = new RemoveLinkThread(l);
//        RemoveLinkThread r50 = new RemoveLinkThread(l);
//        RemoveLinkThread r51 = new RemoveLinkThread(l);
//        RemoveLinkThread r52 = new RemoveLinkThread(l);
//
//        CreateLinkThread r53 = new CreateLinkThread(l);
//        CreateLinkThread r54 = new CreateLinkThread(l);
//        CreateLinkThread r55 = new CreateLinkThread(l);
//        CreateLinkThread r56 = new CreateLinkThread(l);
//        CreateLinkThread r57 = new CreateLinkThread(l);
//        ReadLinkThread r58 = new ReadLinkThread(l);
//        ReadLinkThread r59 = new ReadLinkThread(l);
//        ReadLinkThread r60 = new ReadLinkThread(l);
//        ReadLinkThread r61 = new ReadLinkThread(l);
//        ReadLinkThread r62 = new ReadLinkThread(l);
//        ReadLinkThread r63 = new ReadLinkThread(l);
//        RemoveLinkThread r64 = new RemoveLinkThread(l);
//        RemoveLinkThread r65 = new RemoveLinkThread(l);
//        RemoveLinkThread r66 = new RemoveLinkThread(l);
//        RemoveLinkThread r67 = new RemoveLinkThread(l);
//        RemoveLinkThread r68 = new RemoveLinkThread(l);
//        RemoveLinkThread r69 = new RemoveLinkThread(l);
//
//        r2.start();
//        r3.start();
//        r4.start();
//        r5.start();
//        r6.start();
//        r7.start();
//        r8.start();
//        r9.start();
//        r10.start();
//        r11.start();
//        r12.start();
//        r13.start();
//        r14.start();
//        r15.start();
//        r16.start();
//        r17.start();
//        r18.start();
//
//        r19.start();
//        r20.start();
//        r21.start();
//        r22.start();
//        r23.start();
//        r24.start();
//        r25.start();
//        r26.start();
//        r27.start();
//        r28.start();
//        r29.start();
//        r30.start();
//        r31.start();
//        r32.start();
//        r33.start();
//        r34.start();
//        r35.start();
//
//        r36.start();
//        r37.start();
//        r38.start();
//        r39.start();
//        r40.start();
//        r41.start();
//        r42.start();
//        r43.start();
//        r44.start();
//        r45.start();
//        r46.start();
//        r47.start();
//        r48.start();
//        r49.start();
//        r50.start();
//        r51.start();
//        r52.start();
//
//        r53.start();
//        r54.start();
//        r55.start();
//        r56.start();
//        r57.start();
//        r58.start();
//        r59.start();
//        r60.start();
//        r61.start();
//        r62.start();
//        r63.start();
//        r64.start();
//        r65.start();
//        r66.start();
//        r67.start();
//        r68.start();
//        r69.start();
//
//        r2.join();
//        r3.join();
//        r4.join();
//        r5.join();
//        r6.join();
//        r7.join();
//        r8.join();
//        r9.join();
//        r10.join();
//        r11.join();
//        r12.join();
//        r13.join();
//        r14.join();
//        r15.join();
//        r16.join();
//        r17.join();
//        r18.join();
//
//        r19.join();
//        r20.join();
//        r21.join();
//        r22.join();
//        r23.join();
//        r24.join();
//        r25.join();
//        r26.join();
//        r27.join();
//        r28.join();
//        r29.join();
//        r30.join();
//        r31.join();
//        r32.join();
//        r33.join();
//        r34.join();
//        r35.join();
//
//        r19.join();
//        r20.join();
//        r21.join();
//        r22.join();
//        r23.join();
//        r24.join();
//        r25.join();
//        r26.join();
//        r27.join();
//        r28.join();
//        r29.join();
//        r30.join();
//        r31.join();
//        r32.join();
//        r33.join();
//        r34.join();
//        r35.join();
//
//        r36.join();
//        r37.join();
//        r38.join();
//        r39.join();
//        r40.join();
//        r41.join();
//        r42.join();
//        r43.join();
//        r44.join();
//        r45.join();
//        r46.join();
//        r47.join();
//        r48.join();
//        r49.join();
//        r50.join();
//        r51.join();
//        r52.join();
//
//        r53.join();
//        r54.join();
//        r55.join();
//        r56.join();
//        r57.join();
//        r58.join();
//        r59.join();
//        r60.join();
//        r61.join();
//        r62.join();
//        r63.join();
//        r64.join();
//        r65.join();
//        r66.join();
//        r67.join();
//        r68.join();
//        r69.join();
//        end = System.currentTimeMillis();
//        System.out.println("Time taken for RadixLink :" + (end - start));
//
//        //		Thread.sleep(40000);
//        return (end - start);
//
//
//    }
//
//}
//
//
//
//
//
//class CreateLinkThread extends TestData {
//    Edge r;
//    public CreateLinkThread(Edge r) {
//        this.r = r;
//    }
//    @Override
//    public void run() {
//        Random randomGenerator = new Random();
//        for (int idx = 1; idx <= TrieMap.OSIZE; ++idx){
//            int randomInt = randomGenerator.nextInt(Integer.MAX_VALUE);
//            String key = randomInt+"1";
//            Node created = r.createLink(key.hashCode(), 1, key, key);
//        }
//    }
//}
//
//class ReadLinkThread extends Thread {
//    Edge r;
//    public ReadLinkThread(Edge r) {
//        this.r = r;
//    }
//    @Override
//    public void run() {
//
//        Random randomGenerator = new Random();
//        for (int idx = 1; idx <= TrieMap.OSIZE; ++idx){
//            int randomInt = randomGenerator.nextInt(Integer.MAX_VALUE);
//            String key = randomInt+"1";
//            r.getLink(key, key.hashCode(), 1);
//        }
//    }
//}
//
//
//class RemoveLinkThread extends Thread {
//    Edge r;
//    public RemoveLinkThread(Edge r) {
//        this.r = r;
//    }
//    @Override
//    public void run() {
//
//        Random randomGenerator = new Random();
//        for (int idx = 1; idx <= TrieMap.OSIZE; ++idx){
//            int randomInt = randomGenerator.nextInt(Integer.MAX_VALUE);
//            String key = randomInt+"1";
//            Node n = r.removeLink(key, key.hashCode(), 1);
//        }
//    }
//}
//
//
//class CreateMapThread extends Thread {
//    Map mymap;
//    public CreateMapThread(Map r) {
//        this.mymap = r;
//    }
//    @Override
//    public void run() {
//
//        Random randomGenerator = new Random();
//        for (int idx = 1; idx <= TrieMap.OSIZE; ++idx){
//            int randomInt = randomGenerator.nextInt(Integer.MAX_VALUE);
//            String key = randomInt+"2";
//            mymap.put(key, key);
//        }
//    }
//}
//
//
//class ReadMapThread extends Thread {
//    Map mymap;
//    public ReadMapThread(Map r) {
//        this.mymap = r;
//    }
//    @Override
//    public void run() {
//
//        Random randomGenerator = new Random();
//        for (int idx = 1; idx <= TrieMap.OSIZE; ++idx){
//            int randomInt = randomGenerator.nextInt(Integer.MAX_VALUE);
//            String key = randomInt+"2";
//            mymap.get(randomInt+"2");
//        }
//    }
//}
//
//
//class RemoveMapThread extends Thread {
//    Map mymap;
//    public RemoveMapThread(Map r) {
//        this.mymap = r;
//    }
//    @Override
//    public void run() {
//        Random randomGenerator = new Random();
//        for (int idx = 1; idx <= TrieMap.OSIZE; ++idx){
//            int randomInt = randomGenerator.nextInt(Integer.MAX_VALUE);
//            String key = randomInt+"2";
//            mymap.remove(randomInt+"2");
//        }
//    }
//}
