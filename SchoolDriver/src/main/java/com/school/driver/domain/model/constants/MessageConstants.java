package com.school.driver.domain.model.constants;

public interface MessageConstants {

    String MESSAGE_INTERNAL_ERROR = "msg.internal.not.mapped.error";

    String MESSAGE_STUDENT_NAME_NEEDED = "msg.student.name.needed";

    String MESSAGE_FATHER_NAME_NEEDED = "msg.student.father.name.needed";

    String MESSAGE_MOTHER_NAME_NEEDED = "msg.student.mother.name.needed";

    String MESSAGE_STUDENT_ADDRESS_NEEDED = "msg.student.address.needed";

    String MESSAGE_STUDENT_CREATE_WITH_ID_ERROR = "msg.student.create.with.id.error";

    String MESSAGE_STUDENT_CREATE_WITHOUT_NEEDED_FIELDS = "msg.student.create.without.needed.fields.error";

    String MESSAGE_STUDENT_CREATE_WITHOUT_RESPONSIBLE_PHONE = "msg.student.create.without.responsible.phone.error";

    String MESSAGE_STUDENT_UPDATE_WITHOUT_ID_ERROR = "msg.student.update.without.id.error";

    String MESSAGE_STUDENT_STATUS_UPDATE_TO_DELETED_ERROR = "msg.student.update.status.to.deleted.error";

    String MESSAGE_STUDENT_ALREADY_DELETED_ERROR = "msg.student.already.deleted.error";

    String MESSAGE_STUDENT_NOT_FOUND_ERROR = "msg.student.not.found.error";

    String MESSAGE_ROUTE_TYPE_NEEDED = "msg.route.type.needed";

    String MESSAGE_ROUTE_NOT_FOUND_ERROR = "msg.route.not.found.error";

    String MESSAGE_ROUTE_ALREADY_REMOVED_ERROR = "msg.route.already.removed.error";

    String MESSAGE_ROUTE_CONTAINS_STUDENTS_ERROR = "msg.route.contains.students.error";

    String MESSAGE_ROUTE_ALREADY_FINALIZED_ERROR = "msg.route.already.finalized.error";

    String MESSAGE_ROUTE_REMOVED_ERROR = "msg.route.removed.error";

    String MESSAGE_ROUTE_WITHOUT_LINKED_ROUTE_ERROR = "msg.route.without.linked.route.error";

    String MESSAGE_ROUTE_NOT_INITIALIZED_STATUS_ERROR = "msg.route.not.initialized.error";

    String MESSAGE_ROUTE_LINKED_ROUTE_NEED_IS_FINALIZED_ERROR = "msg.route.linked.need.is.finalized.error";

    String MESSAGE_ROUTE_STUDENT_ALREADY_ON_ROUTE_ERROR = "msg.route.student.already.on.route.error";

    String MESSAGE_ROUTE_WITHOUT_THIS_STUDENT_ERROR = "msg.route.without.this.student.error";

    String MESSAGE_ROUTE_CANT_INCLUDE_NOT_ENABLED_ERROR = "msg.route.cant.include.not.enable.student.error";

    String MESSAGE_ADDRESS_POSTAL_CODE_NEEDED = "msg.address.postal.code.needed";

    String MESSAGE_ADDRESS_STREET_NEEDED = "msg.address.street.needed";

    String MESSAGE_ADDRESS_NEIGHBORHOOD_NEEDED = "msg.neighborhood.street.needed";

    String MESSAGE_ADDRESS_CITY_NEEDED = "msg.address.city.needed";

    String MESSAGE_ADDRESS_STATE_NEEDED = "msg.address.state.needed";

    String MESSAGE_ADDRESS_WITHOUT_NEEDED_FIELDS_ERROR = "msg.address.without.needed.fields.error";

    String MESSAGE_POSTAL_CODE_API_FIND_ERROR = "msg.postal.code.find.error";

    String MESSAGE_POSTAL_CODE_API_NOT_FOUND_ERROR = "msg.postal.code.not.found.error";
}
