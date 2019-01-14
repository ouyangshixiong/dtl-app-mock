/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { DtlAppMockTestModule } from '../../../test.module';
import { EnterpriseInfoUpdateComponent } from 'app/entities/enterprise-info/enterprise-info-update.component';
import { EnterpriseInfoService } from 'app/entities/enterprise-info/enterprise-info.service';
import { EnterpriseInfo } from 'app/shared/model/enterprise-info.model';

describe('Component Tests', () => {
    describe('EnterpriseInfo Management Update Component', () => {
        let comp: EnterpriseInfoUpdateComponent;
        let fixture: ComponentFixture<EnterpriseInfoUpdateComponent>;
        let service: EnterpriseInfoService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [DtlAppMockTestModule],
                declarations: [EnterpriseInfoUpdateComponent]
            })
                .overrideTemplate(EnterpriseInfoUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(EnterpriseInfoUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(EnterpriseInfoService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new EnterpriseInfo(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.enterpriseInfo = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.update).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );

            it(
                'Should call create service on save for new entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new EnterpriseInfo();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.enterpriseInfo = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.create).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );
        });
    });
});
