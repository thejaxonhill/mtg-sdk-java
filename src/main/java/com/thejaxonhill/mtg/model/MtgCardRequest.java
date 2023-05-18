package com.thejaxonhill.mtg.model;

import java.util.function.Consumer;

import com.thejaxonhill.mtg.model.MtgExpression.MtgExpressionBuilder;
import com.thejaxonhill.mtg.shared.MutableBuilder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class MtgCardRequest {

    public static class MtgCardRequestBuilder implements MutableBuilder<MtgCardRequestBuilder> {

        /**
         * The artist of the card. This may not 
         * match what is on the card as MTGJSON 
         * corrects many card misprints.
         * 
         * @param colors String of artists
         * @return The updated builder instance
         * 
         */
        public MtgCardRequestBuilder artist(String artist) {
            this.artist = artist;
            return this;
        }

        public MtgCardRequestBuilder artist(MtgExpression expression) {
            return artist(expression.getExpression());
        }

        public MtgCardRequestBuilder artist(Consumer<MtgExpressionBuilder> mutator) {
            return artist(MtgExpression.builder().applyMutation(mutator).build());
        }

        /**
         * Converted mana cost. Always a number
         * 
         * @param cmc mana cost
         * @return The updated builder instance
         * 
         */
        public MtgCardRequestBuilder cmc(Long cmc) {
            this.cmc = cmc;
            return this;
        }

        /**
         * The card colors. Usually this is derived 
         * from the casting cost, but some cards are 
         * special (like the back of dual sided cards 
         * and Ghostfire).
         * 
         * @param colors single color or expression
         * @return The updated builder instance
         * 
         */
        public MtgCardRequestBuilder colors(String colors) {
            this.colors = colors;
            return this;
        }

        public MtgCardRequestBuilder colors(MtgExpression expression) {
            return colors(expression.getExpression());
        }

        public MtgCardRequestBuilder colors(Consumer<MtgExpressionBuilder> mutator) {
            return colors(MtgExpression.builder().applyMutation(mutator).build());
        }

        /**
         * The card’s color identity, by color code.
         * [“Red”, “Blue”] becomes [“R”, “U”]. A card’s
         * color identity includes colors from the
         * card’s rules text.
         * 
         * @param colorIdentity single color identity or expression
         * @return The updated builder instance
         * 
         */
        public MtgCardRequestBuilder colorIdentity(String colorIdentity) {
            this.colorIdentity = colorIdentity;
            return this;
        }

        public MtgCardRequestBuilder colorIdentity(MtgExpression expression) {
            return colorIdentity(expression.getExpression());
        }

        public MtgCardRequestBuilder colorIdentity(Consumer<MtgExpressionBuilder> mutator) {
            return colorIdentity(MtgExpression.builder().applyMutation(mutator).build());
        }

        /**
         * Filter cards based on whether or not
         * they have a specific field available
         * (like imageUrl or imageUrl,multiverseId)
         * 
         * @param contains single field or expression
         * @return The updated builder instance
         * 
         */
        public MtgCardRequestBuilder contains(String contains) {
            this.contains = contains;
            return this;
        }

        public MtgCardRequestBuilder contains(MtgExpression expression) {
            return contains(expression.getExpression());
        }

        public MtgCardRequestBuilder contains(Consumer<MtgExpressionBuilder> mutator) {
            return contains(MtgExpression.builder().applyMutation(mutator).build());
        }

        /**
         * The flavor text of the card.
         * 
         * @param flavor single flavor text or expression
         * @return The updated builder instance
         * 
         */
        public MtgCardRequestBuilder flavor(String flavor) {
            this.flavor = flavor;
            return this;
        }

        public MtgCardRequestBuilder flavor(MtgExpression expression) {
            return contains(expression.getExpression());
        }

        public MtgCardRequestBuilder flavor(Consumer<MtgExpressionBuilder> mutator) {
            return flavor(MtgExpression.builder().applyMutation(mutator).build());
        }

        /**
         * The game format, such as Commander, 
         * Standard, Legacy, etc. (when used, 
         * legality defaults to Legal unless 
         * supplied)
         * 
         * @param gameFormat single game format or expression
         * @return The updated builder instance
         * 
         */
        public MtgCardRequestBuilder gameFormat(String gameFormat) {
            this.gameFormat = gameFormat;
            return this;
        }

        public MtgCardRequestBuilder gameFormat(MtgExpression expression) {
            return gameFormat(expression.getExpression());
        }

        public MtgCardRequestBuilder gameFormat(Consumer<MtgExpressionBuilder> mutator) {
            return gameFormat(MtgExpression.builder().applyMutation(mutator).build());
        }

        /**
         * A unique id for this card. It is 
         * made up by doing an SHA1 hash of 
         * setCode + cardName + cardImageName
         * 
         * @param id 
         * @return The updated builder instance
         * 
         */
        public MtgCardRequestBuilder id(String id) {
            this.id = id;
            return this;
        }

        /**
         * The card layout. Possible values: 
         * normal, split, flip, double-faced, 
         * token, plane, scheme, phenomenon, 
         * leveler, vanguard, aftermath
         * 
         * @param layout layout enum
         * @return The updated builder instance
         * 
         */
        public MtgCardRequestBuilder layout(Layout layout) {
            this.layout = layout;
            return this;
        }

        /**
         * The language the card is printed in. 
         * Use this parameter along with the name 
         * parameter when searching by foreignName
         * 
         * @param language single language or expression
         * @return The updated builder instance
         * 
         */
        public MtgCardRequestBuilder language(String language) {
            this.language = language;
            return this;
        }

        public MtgCardRequestBuilder language(MtgExpression expression) {
            return language(expression.getExpression());
        }

        public MtgCardRequestBuilder language(Consumer<MtgExpressionBuilder> mutator) {
            return language(MtgExpression.builder().applyMutation(mutator).build());
        }

        /**
         * The legality of the card for a given format, 
         * such as Legal, Banned or Restricted.
         * 
         * @param legality single legality or expression
         * @return The updated builder instance
         * 
         */
        public MtgCardRequestBuilder legality(String legality) {
            this.legality = legality;
            return this;
        }

        public MtgCardRequestBuilder legality(MtgExpression expression) {
            return legality(expression.getExpression());
        }

        public MtgCardRequestBuilder legality(Consumer<MtgExpressionBuilder> mutator) {
            return legality(MtgExpression.builder().applyMutation(mutator).build());
        }

        /**
         * The loyalty of the card. This is only 
         * present for planeswalkers.
         * 
         * @param loyalty single loyalty or expression
         * @return The updated builder instance
         * 
         */
        public MtgCardRequestBuilder loyalty(String loyalty) {
            this.loyalty = loyalty;
            return this;
        }

        public MtgCardRequestBuilder loyalty(MtgExpression expression) {
            return loyalty(expression.getExpression());
        }

        public MtgCardRequestBuilder loyalty(Consumer<MtgExpressionBuilder> mutator) {
            return loyalty(MtgExpression.builder().applyMutation(mutator).build());
        }

        /**
         * The multiverseid of the card on 
         * Wizard’s Gatherer web page. Cards 
         * from sets that do not exist on 
         * Gatherer will NOT have a 
         * multiverseid. Sets not on Gatherer 
         * are: ATH, ITP, DKM, RQS, DPA and 
         * all sets with a 4 letter code that 
         * starts with a lowercase ‘p’.
         * 
         * @param multiverseid string of id
         * @return The updated builder instance
         * 
         */
        public MtgCardRequestBuilder multiverseid(String multiverseid) {
            this.multiverseid = multiverseid;
            return this;
        }

        /**
         * The card name. For split, double-
         * faced and flip cards, just the name 
         * of one side of the card. Basically 
         * each ‘sub-card’ has its own record.
         * 
         * @param name single name or expression
         * @return The updated builder instance
         * 
         */
        public MtgCardRequestBuilder name(String name) {
            this.name = name;
            return this;
        }

        public MtgCardRequestBuilder name(MtgExpression expression) {
            return name(expression.getExpression());
        }

        public MtgCardRequestBuilder name(Consumer<MtgExpressionBuilder> mutator) {
            return name(MtgExpression.builder().applyMutation(mutator).build());
        }

        /**
         * The card number. This is printed at 
         * the bottom-center of the card in 
         * small text. This is a string, not an 
         * integer, because some cards have 
         * letters in their numbers.
         * 
         * @param number single number or expression
         * @return The updated builder instance
         * 
         */
        public MtgCardRequestBuilder number(String number) {
            this.number = number;
            return this;
        }

        public MtgCardRequestBuilder number(MtgExpression expression) {
            return number(expression.getExpression());
        }

        public MtgCardRequestBuilder number(Consumer<MtgExpressionBuilder> mutator) {
            return number(MtgExpression.builder().applyMutation(mutator).build());
        }

         /**
         * The field to order by in the response 
         * results
         * 
         * @param orderBy field to order by
         * @return The updated builder instance
         * 
         */
        public MtgCardRequestBuilder orderBy(String orderBy) {
            this.orderBy = orderBy;
            return this;
        }

        /**
         * The page of data to request
         * 
         * @param page page number
         * @return The updated builder instance
         * 
         */
        public MtgCardRequestBuilder page(int page) {
            this.page = page;
            return this;
        }

        /**
         * The amount of data to return in a 
         * single request. The default (and 
         * max) is 100.
         * 
         * @param pageSize page size
         * @return The updated builder instance
         * 
         */
        public MtgCardRequestBuilder pageSize(int pageSize) {
            this.pageSize = pageSize;
            return this;
        }

        /**
         * The power of the card. This is only 
         * present for creatures. This is a 
         * string, not an integer, because some 
         * cards have powers like: “1+*”
         * 
         * @param power power of card
         * @return The updated builder instance
         * 
         */
        public MtgCardRequestBuilder power(String power) {
            this.power = power;
            return this;
        }

        /**
         * The rarity of the card. Examples: 
         * Common, Uncommon, Rare, Mythic 
         * Rare, Special, Basic Land
         * 
         * @param rarity single rarity or expression
         * @return The updated builder instance
         * 
         */
        public MtgCardRequestBuilder rarity(String rarity) {
            this.rarity = rarity;
            return this;
        }

        public MtgCardRequestBuilder rarity(MtgExpression expression) {
            return rarity(expression.getExpression());
        }

        public MtgCardRequestBuilder rarity(Consumer<MtgExpressionBuilder> mutator) {
            return rarity(MtgExpression.builder().applyMutation(mutator).build());
        }

        /**
         * Fetch any number of cards 
         * (controlled by pageSize) randomly
         * 
         * @param random bool
         * @return The updated builder instance
         * 
         */
        public MtgCardRequestBuilder random(Boolean random) {
            this.random = random;
            return this;
        }
    }

    private String artist;

    private Long cmc;

    private String colors;

    private String colorIdentity;

    private String contains;

    private String flavor;

    private String gameFormat;

    private String id;

    private Layout layout;

    private String language;

    private String legality;

    private String loyalty;

    private String multiverseid;

    private String name;

    private String number;

    private String orderBy;

    private Integer page;

    private Integer pageSize;

    private String power;

    private String rarity;

    private Boolean random;

    private String set;

    private String setName;

    private String superTypes;

    private String subTypes;

    private String text;

    private String toughness;

    private String type;

    private String types;

}
