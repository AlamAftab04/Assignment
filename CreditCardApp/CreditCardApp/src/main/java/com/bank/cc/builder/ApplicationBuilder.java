package com.bank.cc.builder;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Component;

import com.bank.cc.dao.model.ApplicationEntity;
import com.bank.cc.dao.model.BalanceEntity;
import com.bank.cc.dao.model.CreditCardEntity;
import com.bank.cc.dao.model.UserEntity;
import com.bank.cc.model.ApplicationRequest;
import com.bank.cc.model.Balance;
import com.bank.cc.model.CardStatus;
import com.bank.cc.model.CreditCard;
import com.bank.cc.model.CreditCategory;
import com.bank.cc.model.User;

@Component
public class ApplicationBuilder {

	public ApplicationEntity buildApplicationEnityFromApplication(ApplicationRequest application) {

		return ApplicationEntity.builder().applicationId(application.getApplicationId()).email(application.getEmail())
				.firstName(application.getFirstName()).lastName(application.getLastName()).ssn(application.getSsn())
				.phoneNumber(application.getPhoneNumber()).submissionDate(Calendar.getInstance().getTime()).build();
	}

	public UserEntity buildUserFromApplicationAndCreditCard(CreditCardEntity card, ApplicationRequest ap) {

		UserEntity user = UserEntity.builder().email(ap.getEmail()).firstName(ap.getFirstName())
				.lastName(ap.getLastName()).ssn(ap.getSsn()).phoneNumber(ap.getPhoneNumber()).build();
		List<CreditCardEntity> cards = new ArrayList<>();
		cards.add(card);
		user.setCards(cards);
		return user;
	}

	@SuppressWarnings("deprecation")
	public CreditCardEntity buildCreditCard(long newCreditCardNumber, int rank) {
		CreditCardEntity creditCard = CreditCardEntity.builder().cardNumber(newCreditCardNumber)
				.cvv(new Random().nextInt(900) + 100).status(CardStatus.ACTIVE.toString()).build();
		switch (rank) {
		case 0: {
			creditCard.setCreditCategory(CreditCategory.HIGH.toString());
			creditCard.setExpiryMonth(Calendar.getInstance().getTime().getMonth() + 1);
			creditCard.setExpiryYear(Calendar.getInstance().getTime().getYear() + 1905);
			creditCard.setBalance(buildHighBalance());
			break;
		}
		case 1: {
			creditCard.setCreditCategory(CreditCategory.MEDIUM.toString());
			creditCard.setExpiryMonth(Calendar.getInstance().getTime().getMonth() + 1);
			creditCard.setExpiryYear(Calendar.getInstance().getTime().getYear() + 1903);
			creditCard.setBalance(buildMediumBalance());
			break;
		}
		case 2: {
			creditCard.setCreditCategory(CreditCategory.LOW.toString());
			creditCard.setExpiryMonth(Calendar.getInstance().getTime().getMonth() + 1);
			creditCard.setExpiryYear(Calendar.getInstance().getTime().getYear() + 1902);
			creditCard.setBalance(buildLowBalance());
			break;
		}
		}
		return creditCard;
	}

	private BalanceEntity buildMediumBalance() {
		return BalanceEntity.builder().cardLimit(new Double(30000)).cardLimitAvailable(new Double(30000))
				.cashLimit(new Double(300)).cashLimitAvailable(new Double(300)).outstandingBillAmount(new Double(0))
				.unbilledAmount(new Double(0)).build();
	}

	private BalanceEntity buildLowBalance() {
		return BalanceEntity.builder().cardLimit(new Double(10000)).cardLimitAvailable(new Double(10000))
				.cashLimit(new Double(0)).cashLimitAvailable(new Double(0)).outstandingBillAmount(new Double(0))
				.unbilledAmount(new Double(0)).build();
	}

	private BalanceEntity buildHighBalance() {
		return BalanceEntity.builder().cardLimit(new Double(50000)).cardLimitAvailable(new Double(50000))
				.cashLimit(new Double(500)).cashLimitAvailable(new Double(500)).outstandingBillAmount(new Double(0))
				.unbilledAmount(new Double(0)).build();
	}

	public User buildResponseFromUserEntity(UserEntity ue) {
		User user = User.builder().userId(ue.getUserId()).firstName(ue.getFirstName()).lastName(ue.getLastName())
				.email(ue.getEmail()).phoneNumber(ue.getPhoneNumber()).build();
		user.setCards(buildCardsFromCardEntities(ue.getCards()));
		return user;
	}

	private List<CreditCard> buildCardsFromCardEntities(List<CreditCardEntity> cards) {
		List<CreditCard> creditCards = new ArrayList<>(cards.size());
		if (!cards.isEmpty()) {
			for (CreditCardEntity cc : cards) {
				CreditCard creditCard = CreditCard.builder().cardNumber(cc.getCardNumber())
						.creditCategory(CreditCategory.fromValue(cc.getCreditCategory())).cvv(cc.getCvv())
						.expiryMonth(cc.getExpiryMonth()).expiryYear(cc.getExpiryYear())
						.status(CardStatus.fromValue(cc.getStatus())).build();
				creditCard.setBalance(buildBalanceFromBalanceEntity(cc.getBalance()));
				creditCards.add(creditCard);
			}
		}

		return creditCards;
	}

	private Balance buildBalanceFromBalanceEntity(BalanceEntity balance) {
		return Balance.builder().cardLimit(balance.getCardLimit()).cardLimitAvailable(balance.getCardLimitAvailable())
				.cashLimit(balance.getCashLimit()).cashLimitAvailable(balance.getCashLimitAvailable()).build();
	}
}
