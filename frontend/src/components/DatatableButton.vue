<template>
    <span>
        <b-button v-if="createButton" variant="primary" @click='$emit("click")'>
            <b-icon-plus/>
            <template v-if="label">{{ label }}</template>
            <template v-else>Oluştur</template>
        </b-button>
        <b-button v-if="editButton" size="sm" variant="warning" @click='$emit("click")'>
            <b-icon-pencil/>
            <template v-if="label">{{ label }}</template>
            <template v-else>Düzenle</template>
        </b-button>
        <b-button v-if="deleteButton" size="sm" variant="danger" @click='$emit("click")'>
            <b-icon-trash/>
            <template v-if="label">{{ label }}</template>
            <template v-else>Sil</template>
        </b-button>
    </span>
</template>

<script>
export default {
    name: 'DatatableButton',
    props: {
        editButton: Boolean,
        deleteButton: Boolean,
        createButton: Boolean,
        label: String
    }
}
</script>
