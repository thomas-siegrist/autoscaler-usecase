-- Kategorien:
INSERT INTO category(id, name) VALUES (0, 'Schwein');
INSERT INTO category(id, name) VALUES (1, 'Rind');
INSERT INTO category(id, name) VALUES (2, 'Kalb');
INSERT INTO category(id, name) VALUES (3, 'Pferd');
INSERT INTO category(id, name) VALUES (4, 'Huhn');
INSERT INTO category(id, name) VALUES (5, 'Lamm');
INSERT INTO category(id, name) VALUES (6, 'Truthahn');
INSERT INTO category(id, name) VALUES (7, 'Gans');
INSERT INTO category(id, name) VALUES (8, 'Ente');
INSERT INTO category(id, name) VALUES (9, 'Fisch');
INSERT INTO category(id, name) VALUES (10, 'Meerestiere');

-- Schweinefleisch-Sorten
INSERT INTO article(id,category_id, name, description, price, stock) VALUES (0,0, 'Kopf', 'Diese beiden Partien bieten herzhaftes, relativ fettes, durchwachsenes Fleisch (Backe) und gelten vor allem als regionale Spezialitäten. ', 1.0, 1000);
INSERT INTO article(id,category_id, name, description, price, stock) VALUES (1,0, 'Nacken', 'Der Nacken ist die Verlängerung des Kotelettstranges und heißt auch Schweinehals oder Kamm. Das Fleisch ist sehr beliebt, denn es ist schön marmoriert, also gut durchwachsen, und besonders saftig. ', 1.5, 1000);
INSERT INTO article(id,category_id, name, description, price, stock) VALUES (2,0, 'Schulter', 'Diese Partie mit grobfaserigem Muskelfleisch ist auch als Bug oder Blatt bekannt, sie schließt sich direkt an den Nacken an und hat einen recht hohen Sehnenanteil. ', 2.4, 1000);
INSERT INTO article(id,category_id, name, description, price, stock) VALUES (3,0, 'Rücken', 'Die fette Decke über dem Kotelettstrang wird auch Kernstück, fetter Speck oder „grüner Speck“ genannt. ', 3.0, 1000);
INSERT INTO article(id,category_id, name, description, price, stock) VALUES (4,0, 'Kotellet', 'Der Schweinerücken, aus dem der Kotelettstrang (auch Karrée oder Karbonade genannt) mit dem innen liegenden Filet stammt, bietet zartes und mageres Fleisch und ist eines der edelsten Teilstücke des Schweins. ', 3.5, 1000);
INSERT INTO article(id,category_id, name, description, price, stock) VALUES (5,0, 'Filet', 'Das feinste und zarteste Fleisch vom Schwein ist das Filet, auch Lende genannt. Es sitzt an der Unterseite des hinteren Kotelettstranges. Das Fleisch ist fettarm, zart und saftig. ', 8.5, 1000);
INSERT INTO article(id,category_id, name, description, price, stock) VALUES (6,0, 'Schinken', 'Der Schinken (Keule) bietet die erstklassigen saftigen, mageren Fleischarten Oberschale, Unterschale, Nussschinken und den Schinkenspeck. ', 5.4, 1000);
INSERT INTO article(id,category_id, name, description, price, stock) VALUES (7,0, 'Eisbein', 'Die Haxe des Schweins ist Bestandteil von Schulter und Schinken und wird auch als Eisbein/Schinkeneisbein bezeichnet.', 1.2, 1000);
INSERT INTO article(id,category_id, name, description, price, stock) VALUES (8,0, 'Bauch', 'Kräftig durchwachsen und von Rippenknochen durchzogen ist der Schweinebauch, eine beliebte und häufig nachgefragte Partie, die auch als Wammerl bezeichnet wird. ', 3.3, 1000);
INSERT INTO article(id,category_id, name, description, price, stock) VALUES (9,0, 'Rippe', 'Diese grobfaserige Fleischspezialität zwischen Bauch und Schulter ist an der Brustspitze angesiedelt und meist deutlich mit Fett durchzogen.', 4.5, 1000);

-- Rindfleisch-Sorten,,category stock,
INSERT INTO article(id,category_id, name, description, price, stock) VALUES (10,1, 'Kamm', 'Die obere Halspartie heißt auch Nacken und hat kräftiges, durchwachsenes Fleisch. Es ist sehr saftig und kann gut gereift ein Steak ersetzen.', 3.3, 1000);
INSERT INTO article(id,category_id, name, description, price, stock) VALUES (11,1, 'Rippe', 'Auch wenn hier recht viel Fett dabei ist – die Hohe Rippe zählt zu den beliebten Teilen vom Rind. Ihr Fleisch ist von feinen Fettäderchen durchzogen, die es saftig und sehr schmackhaft machen. „Obendrauf“ ist die dickere Fettschicht.', 3.7, 1000);
INSERT INTO article(id,category_id, name, description, price, stock) VALUES (12,1, 'Roasbeef', 'Das auch Zwischenrippenstück genannte Roastbeef hat einen mageren Fleischkern und von feinen Fettäderchen durchzogenes Muskelfleisch. Es ist nach dem Filet das Wertvollste vom Rind. ', 5.3, 1000);
INSERT INTO article(id,category_id, name, description, price, stock) VALUES (13,1, 'Filet', ' Unterhalb des Rückens liegt das beste und feinste Stück vom Rind. Der verhältnismäßig kleine, keulenförmige Muskelstrang an beiden Seiten der Wirbelsäule bietet äußerst zartes, mageres und saftiges Fleisch für die Gourmetküche. ', 9.8, 1000);
INSERT INTO article(id,category_id, name, description, price, stock) VALUES (14,1, 'Kugel', 'Dieses Teilstück der Keule heißt auch Nuss und besteht aus drei feinfaserigen Schichten/Lagen, die zusammen lassen oder einzeln zubereitet werden können. Das Fleisch ist fein, hochwertig, sehr fettarm und trocken. ', 7.6, 1000);
INSERT INTO article(id,category_id, name, description, price, stock) VALUES (15,1, 'Keule', 'Die Keule besteht aus der Innenseite („Oberschale“) und der Außenseite („Unterschale“) und bietet hervorragendes Fleisch. Zusammen mit Kugel, Hüfte und Blume stellt die Keule den größten Fleischanteil beim Rind.', 2.2, 1000);
INSERT INTO article(id,category_id, name, description, price, stock) VALUES (16,1, 'Bauch', 'Ob Flanke, Dünnung oder Bauchlappen, damit ist immer der fett- und bindegewebsreiche Teil des Rindes gemeint. Das grobfaserige Fleisch ist gut durchwachsen.', 2.2, 1000);
INSERT INTO article(id,category_id, name, description, price, stock) VALUES (17,1, 'Querrippe', 'Die Querrippe und die Spannrippe bilden den oberen Brustkorb mit gutem Kochfleisch, da sich die Knochen beim Kochen sehr gut herauslösen lassen. ', 3.4, 1000);
INSERT INTO article(id,category_id, name, description, price, stock) VALUES (18,1, 'Schulter', 'Gleich vier verschiedene Teilstücke bietet die Rinderschulter mit dem dicken Bugstück, dem Schaufelstück, dem falschen Filet und dem Restbug. Das Fleisch aus dieser Partie ist wenig durchwachsen, das „falsche Filet“ ist grobfaseriger und kompakter als das „richtige“ Filet. ', 6.5, 1000);
INSERT INTO article(id,category_id, name, description, price, stock) VALUES (19,1, 'Hesse', 'Dieser Teil des Unterschenkels wird auch Wade genannt, der Fleischanteil ist eher gering und mit Bindegewebe durchzogen, frisch ist das Fleisch der Hesse dunkelrot. ', 3.4, 1000);

-- Kunden
INSERT INTO customer(id, first_name, last_name, email) VALUES (0, 'Homer', 'Simpson', 'homer.simpson@gmail.com');
INSERT INTO customer(id, first_name, last_name, email) VALUES (1, 'Bart', 'Simpson', 'bart.simpson@gmail.com');
INSERT INTO customer(id, first_name, last_name, email) VALUES (2, 'Marge', 'Simpson', 'marge.simpson@gmail.com');
INSERT INTO customer(id, first_name, last_name, email) VALUES (3, 'Lisa', 'Simpson', 'lisa.simpson@gmail.com');
INSERT INTO customer(id, first_name, last_name, email) VALUES (4, 'Mr', 'Burns', 'mr.burns@gmail.com');